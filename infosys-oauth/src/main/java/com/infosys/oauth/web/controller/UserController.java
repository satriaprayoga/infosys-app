package com.infosys.oauth.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.oauth.dto.ApiResponse;
import com.infosys.oauth.dto.BackendSignUpRequest;
import com.infosys.oauth.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getUserInfo(OAuth2Authentication user){
		Map<String, Object> userInfo=new HashMap<>();
		userInfo.put("username", user.getPrincipal());
		userInfo.put("authorities", user.getAuthorities());
		return ResponseEntity.ok(userInfo);
	}
	
	@PostMapping
	public ApiResponse registerBackend(@Valid @RequestBody BackendSignUpRequest request) {
		return new ApiResponse(HttpStatus.OK, userService.registerBackend(request));
	}
}
