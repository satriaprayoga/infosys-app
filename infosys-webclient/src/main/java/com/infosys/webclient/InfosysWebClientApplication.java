package com.infosys.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.infosys.webclient.config.AppProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties(AppProperties.class)
public class InfosysWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfosysWebClientApplication.class, args);
	}
	
	/*
	 * @Autowired private AppProperties appProperties;
	 * 
	 * @Autowired(required = false) ClientHttpRequestFactory
	 * clientHttpRequestFactory;
	 */

	/*
	 * ClientHttpRequestFactory is autowired and checked in case somewhere in
	 * your configuration you provided {@link ClientHttpRequestFactory}
	 * implementation Bean where you defined specifics of your connection, if
	 * not it is instantiated here with {@link SimpleClientHttpRequestFactory}
	 */
	/*
	 * private ClientHttpRequestFactory getClientHttpRequestFactory() { if
	 * (clientHttpRequestFactory == null) { clientHttpRequestFactory = new
	 * SimpleClientHttpRequestFactory(); } return clientHttpRequestFactory; }
	 * 
	 * @Bean public ClientCredentialsResourceDetails
	 * clientCredentialsResourceDetails() { ClientCredentialsResourceDetails
	 * details=new ClientCredentialsResourceDetails();
	 * details.setAccessTokenUri(appProperties.getClient().getUri());
	 * details.setClientId(appProperties.getClient().getId());
	 * details.setGrantType(appProperties.getClient().getGrant());
	 * details.setClientSecret(appProperties.getClient().getSecret());
	 * details.setScope(Collections.singletonList(new
	 * String(appProperties.getClient().getScope()))); return details; }
	 * 
	 * @Bean public OAuth2RestTemplate restTemplate() { OAuth2RestTemplate template
	 * = new OAuth2RestTemplate(clientCredentialsResourceDetails(), new
	 * DefaultOAuth2ClientContext( new DefaultAccessTokenRequest())); return
	 * prepareTemplate(template, true); }
	 * 
	 * public OAuth2RestTemplate prepareTemplate(OAuth2RestTemplate template,
	 * boolean isClient) {
	 * template.setRequestFactory(getClientHttpRequestFactory());
	 * template.setAccessTokenProvider(clientAccessTokenProvider()); return
	 * template; }
	 * 
	 * @Bean public AccessTokenProvider clientAccessTokenProvider() {
	 * ClientCredentialsAccessTokenProvider accessTokenProvider = new
	 * ClientCredentialsAccessTokenProvider();
	 * accessTokenProvider.setRequestFactory(getClientHttpRequestFactory()); return
	 * accessTokenProvider; }
	 */
}
