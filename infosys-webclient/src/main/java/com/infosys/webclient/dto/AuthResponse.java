package com.infosys.webclient.dto;

public class AuthResponse {

	private String accessToken;
    private String tokenType = "Bearer";
    private boolean rememberMe=false;

    public AuthResponse(String accessToken,boolean rememberMe) {
        this.accessToken = accessToken;
        this.rememberMe=rememberMe;
    }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
    
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	public boolean isRememberMe() {
		return rememberMe;
	}
    
}
