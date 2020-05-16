package com.infosys.webclient.config;

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

import com.infosys.webclient.security.CustomUserDetailsService;
import com.infosys.webclient.security.RestAuthenticationEntryPoint;
import com.infosys.webclient.security.TokenAuthenticationFilter;
import com.infosys.webclient.security.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter(tokenProvider, customUserDetailsService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
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
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
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
			.antMatchers("/auth/**","/oauth2/**","/register/**")
			.permitAll()
			.antMatchers("/api/customers")
			.authenticated()
			.anyRequest()
			.authenticated();
//				.and()
//			.oauth2Login()
//				.authorizationEndpoint()
//					.baseUri("/oauth2/authorize")
//					.authorizationRequestRepository(cookieOauth2RequestRepository())
//					.and()
//				.redirectionEndpoint()
//					.baseUri("/oauth2/callback/**")
//					.and()
//				.userInfoEndpoint()
//					.userService(customOAuth2UserService)
//					.and()
//				.successHandler(oAuth2AuthenticationSuccessHandler)
//				.failureHandler(oAuth2AuthenticationFailureHandler);
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
