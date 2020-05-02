package com.infosys.webclient.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration
public class CustomerClientConfig {
	
	@Autowired
	private AppProperties appProperties;
	
	@Bean
	public RequestInterceptor requestInterceptor() {
		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), oAuth2ProtectedResourceDetails());
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new ClientErrorDecoder();
	}
	
	
	public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
		ClientCredentialsResourceDetails details=new ClientCredentialsResourceDetails();
		details.setAccessTokenUri(appProperties.getClient().getUri());
		details.setClientId(appProperties.getClient().getId());
		details.setGrantType(appProperties.getClient().getGrant());
		details.setClientSecret(appProperties.getClient().getSecret());
		details.setScope(Collections.singletonList(new String(appProperties.getClient().getScope())));
		return details;
	}
}
