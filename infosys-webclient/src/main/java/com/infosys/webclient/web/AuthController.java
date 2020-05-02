package com.infosys.webclient.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.webclient.dto.ApiResponse;
import com.infosys.webclient.dto.AuthResponse;
import com.infosys.webclient.dto.CustomerAuthDTO;
import com.infosys.webclient.dto.CustomerDTO;
import com.infosys.webclient.dto.payload.ActivationRequest;
import com.infosys.webclient.dto.payload.ChangePasswordRequest;
import com.infosys.webclient.dto.payload.CustomerRequest;
import com.infosys.webclient.dto.payload.LoginRequest;
import com.infosys.webclient.event.RegistrationEvent;
import com.infosys.webclient.event.RegistrationEventPublisher;
import com.infosys.webclient.exceptions.UserAlreadyExistException;
import com.infosys.webclient.security.TokenProvider;
import com.infosys.webclient.service.CustomerService;
import com.infosys.webclient.utils.RandomUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private TokenProvider tokenProvider;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RegistrationEventPublisher eventPublisher;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
		
	public AuthController() {
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        if(!userDetails.isEnabled()) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("user is not actived, please check your email"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }
	
	@PostMapping("/register")
	public ResponseEntity<CustomerDTO> register(@Valid @RequestBody CustomerRequest request) {
		if(customerService.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistException("email already registered");
		}
		ResponseEntity<CustomerDTO> dto=customerService.register(request);
		if(dto.getStatusCode()==HttpStatus.OK) {
			CustomerDTO cdto=dto.getBody();
			RegistrationEvent event=new RegistrationEvent(cdto.getId(), cdto.getEmail(), cdto.getActivationKey(), request.getActivationUrl());
			event.setType(RegistrationEvent.ACTIVATION);
			eventPublisher.publishRegistrationEvent(event);
		}
		return dto;
	}
	
	
	
	@GetMapping("/exists")
	public ApiResponse existsByEmail(@RequestParam String email){
		return new ApiResponse(HttpStatus.OK, customerService.existsByEmail(email));
	}
	
	@GetMapping("/email")
	public ApiResponse findByEmail(@RequestParam String email) {
		return new ApiResponse(HttpStatus.OK, customerService.findByEmail(email));
	}
	
	@PutMapping("/activate")
	public ApiResponse activate(@Valid @RequestBody ActivationRequest request) {
		return new ApiResponse(HttpStatus.OK, customerService.activate(request));
	}
	
	@PostMapping("/resetPassword")
	public ApiResponse resetPassword(@Valid @RequestBody String request) {
		if(customerService.existsByEmail(request)) {
			CustomerAuthDTO dto=customerService.findByEmail(request);
			ChangePasswordRequest resetPassword=new ChangePasswordRequest();
			resetPassword.setId(dto.getId());
			String password=RandomUtils.generateRandomAlphanumeric(6);
			resetPassword.setPassword(passwordEncoder.encode(password));
			customerService.changePassword(resetPassword);
			RegistrationEvent event=new RegistrationEvent(dto.getId(), dto.getEmail(), password, null);
			event.setType(RegistrationEvent.RESET_PASSWORD);
			eventPublisher.publishRegistrationEvent(event);
			return new ApiResponse(HttpStatus.OK, new String("Reset password will be sent to : "+request));
		}else {
			return new ApiResponse(HttpStatus.OK, new String("Your Email were not registered, please signup "));
		}
		
	}
	
}
