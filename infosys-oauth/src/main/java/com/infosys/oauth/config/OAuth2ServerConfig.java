package com.infosys.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter{

//	static final String CLIEN_ID = "infosys-client";
//	static final String CLIENT_SECRET = "p4l4Wi7a";
//	static final String GRANT_TYPE = "password";
//	static final String AUTHORIZATION_CODE = "authorization_code";
//	static final String REFRESH_TOKEN = "refresh_token";
//	static final String IMPLICIT = "implicit";
//	static final String SCOPE_WEB = "webClient";
//	static final String SCOPE_MOBILE = "mobileClient";
//	static final String TRUST = "trust";
//	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
//    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
    
   
    private TokenStore tokenStore;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public TokenStore tokenStore() {
    	if(tokenStore==null) {
    		tokenStore=new JdbcTokenStore(dataSource);
    	}
    	return tokenStore;
    }
    
    @Bean
    public DefaultTokenServices tokenServices() {
    	DefaultTokenServices tokenServices=new DefaultTokenServices();
    	tokenServices.setClientDetailsService(new JdbcClientDetailsService(dataSource));
    	tokenServices.setTokenStore(tokenStore());
    	tokenServices.setSupportRefreshToken(true);
    	return tokenServices;
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    	clients.inMemory().withClient(CLIEN_ID)
//    					  .secret(passwordEncoder.encode(CLIENT_SECRET))
//    					  .authorizedGrantTypes(GRANT_TYPE,AUTHORIZATION_CODE,REFRESH_TOKEN,IMPLICIT)
//    					  .scopes(SCOPE_WEB,SCOPE_MOBILE,TRUST)
//    					  .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
//    					  .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    	clients.jdbc(dataSource);
    			
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    	security.passwordEncoder(passwordEncoder).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
}
