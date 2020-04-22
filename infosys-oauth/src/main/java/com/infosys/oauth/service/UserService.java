package com.infosys.oauth.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.infosys.oauth.domain.User;
import com.infosys.oauth.dto.BackendSignUpRequest;
import com.infosys.oauth.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Transactional
	public User registerBackend(BackendSignUpRequest request) {
		User user=BackendSignUpRequest.toUser(request);
		return userRepository.save(user);
	}
}
