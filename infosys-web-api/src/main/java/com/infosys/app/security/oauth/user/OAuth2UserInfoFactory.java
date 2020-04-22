package com.infosys.app.security.oauth.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.app.domain.AuthProvider;
import com.infosys.app.security.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {
	
	private static final Logger logger=LoggerFactory.getLogger(OAuth2UserInfoFactory.class);

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        logger.info("############# registrationId: "+registrationId);
		if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
