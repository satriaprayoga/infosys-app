package com.infosys.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.app.domain.User;
import com.infosys.app.repository.UserRepository;
import com.infosys.app.security.CurrentUser;
import com.infosys.app.security.UserPrincipal;
import com.infosys.app.security.exception.ResourceNotFoundException;

@RestController
public class UserController {

	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}
}
