package com.infosys.webclient.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

	private final Auth auth = new Auth();
	private final OAuth2 oauth2 = new OAuth2();
	private final Client client=new Client();

	public static class Auth {
		private String tokenSecret;
		private long tokenExpirationMsec;
		
		public void setTokenExpirationMsec(long tokenExpirationMsec) {
			this.tokenExpirationMsec = tokenExpirationMsec;
		}
		
		public void setTokenSecret(String tokenSecret) {
			this.tokenSecret = tokenSecret;
		}

		public long getTokenExpirationMsec() {
			return tokenExpirationMsec;
		}

		public String getTokenSecret() {
			return tokenSecret;
		}
	}

	public static final class OAuth2 {
		private List<String> authorizedRedirectUris = new ArrayList<>();

		public List<String> getAuthorizedRedirectUris() {
			return authorizedRedirectUris;
		}

		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
			return this;
		}
		
		public void setAuthorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
		}
		
		
	}
	
	public static final class Client{
		private String gateway;
		private String uri;
		private String grant;
		private String id;
		private String secret;
		private String scope;
		
		public String getGateway() {
			return gateway;
		}
		
		public void setGateway(String gateway) {
			this.gateway = gateway;
		}
		
		public String getUri() {
			return uri;
		}
		public String getGrant() {
			return grant;
		}
		public void setGrant(String grant) {
			this.grant = grant;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		
	}

	public Auth getAuth() {
		return auth;
	}

	public OAuth2 getOauth2() {
		return oauth2;
	}
	
	public Client getClient() {
		return client;
	}
}
