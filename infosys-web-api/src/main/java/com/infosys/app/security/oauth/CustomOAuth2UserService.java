package com.infosys.app.security.oauth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.infosys.app.domain.AuthProvider;
import com.infosys.app.domain.User;
import com.infosys.app.repository.UserRepository;
import com.infosys.app.security.UserPrincipal;
import com.infosys.app.security.exception.OAuth2AuthenticationProcessingException;
import com.infosys.app.security.oauth.user.OAuth2UserInfo;
import com.infosys.app.security.oauth.user.OAuth2UserInfoFactory;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User user=super.loadUser(oAuth2UserRequest);
		try {
            return processOAuth2User(oAuth2UserRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
	}
	
	
	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest,OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo=OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from the Authentication Provider");
		}
		
		Optional<User> userOptional=userRepository.findByEmail(oAuth2UserInfo.getEmail());
		User user;
		if(userOptional.isPresent()) {
			user=userOptional.get();
			if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
			}
			user=updateExistingUser(user, oAuth2UserInfo);
		}else {
			user=registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		
		return UserPrincipal.create(user,oAuth2User.getAttributes());
	}
	
	private User registerNewUser(OAuth2UserRequest request, OAuth2UserInfo userInfo) {
		User user=new User();
		user.setProvider(AuthProvider.valueOf(request.getClientRegistration().getRegistrationId()));
		user.setProviderId(userInfo.getId());
		user.setName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getImageUrl());
        return userRepository.save(user);
	}
	
	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

}
