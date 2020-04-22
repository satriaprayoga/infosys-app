package com.infosys.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.infosys.app.security.CustomUserDetailsService;
import com.infosys.app.security.RestAuthenticationEntryPoint;
import com.infosys.app.security.TokenAuthenticationFilter;
import com.infosys.app.security.oauth.CustomOAuth2UserService;
import com.infosys.app.security.oauth.HttpCookieOauth2RequestRepository;
import com.infosys.app.security.oauth.OAuth2AuthenticationFailureHandler;
import com.infosys.app.security.oauth.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    
    @Autowired
    private HttpCookieOauth2RequestRepository httpCookieOauth2RequestRepository;
    
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
    	return new TokenAuthenticationFilter();
    }
    
    @Bean
    public HttpCookieOauth2RequestRepository cookieOauth2RequestRepository() {
    	return new HttpCookieOauth2RequestRepository();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.and()
			.authorizeRequests().antMatchers(
					 "/",
                     "/error",
                     "/favicon.ico",
                     "/**/*.png",
                     "/**/*.gif",
                     "/**/*.svg",
                     "/**/*.jpg",
                     "/**/*.html",
                     "/**/*.css",
                     "/**/*.js")
			.permitAll()
			.antMatchers("/auth/**","/oauth2/**")
			.permitAll()
			.antMatchers("/data/**")
			.permitAll()
			.anyRequest()
			.authenticated()
				.and()
			.oauth2Login()
				.authorizationEndpoint()
					.baseUri("/oauth2/authorize")
					.authorizationRequestRepository(cookieOauth2RequestRepository())
					.and()
				.redirectionEndpoint()
					.baseUri("/oauth2/callback/**")
					.and()
				.userInfoEndpoint()
					.userService(customOAuth2UserService)
					.and()
				.successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler);
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}


