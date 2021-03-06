package com.infosys.webclient.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infosys.webclient.dto.CustomerActivationDTO;
import com.infosys.webclient.dto.CustomerAuthDTO;
import com.infosys.webclient.dto.CustomerDTO;
import com.infosys.webclient.dto.payload.ActivationRequest;
import com.infosys.webclient.dto.payload.ChangePasswordRequest;
import com.infosys.webclient.dto.payload.CustomerInfoRequest;
import com.infosys.webclient.dto.payload.CustomerRequest;
import com.infosys.webclient.exceptions.ResourceNotFoundException;
import com.infosys.webclient.service.clients.CustomerDataService;

@Service
public class CustomerService {

	@Autowired
	private CustomerDataService customerDataService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<CustomerDTO> register(CustomerRequest request){
		String encoded=passwordEncoder.encode(request.getPassword());
		request.setPassword(encoded);
		return customerDataService.register(request);
	}
	
	public CustomerDTO updateCustomerInfo( String id, CustomerInfoRequest request){
		return customerDataService.updateCustomerInfo(id, request).getBody();
	}
	
	public Boolean existsByEmail(String email) {
		return customerDataService.existsByEmail(email).getBody();
	}
	
	public Boolean isProfileCompleted(String id) {
		return customerDataService.isProfileCompleted(id).getBody();
	}
	
	public CustomerAuthDTO findById(String id) {
		CustomerAuthDTO dto=customerDataService.findById(id).getBody();
		if(dto==null) {
			throw new ResourceNotFoundException(CustomerAuthDTO.class, "id");
		}
		return dto;
	}
	
	public CustomerAuthDTO findByEmail(String email) {
		CustomerAuthDTO dto=customerDataService.findByEmail(email).getBody();
		if(dto==null) {
			throw new ResourceNotFoundException(CustomerAuthDTO.class, "email");
		}
		return dto;
	}
	
	public CustomerActivationDTO activate(ActivationRequest request) {
		return customerDataService.activate(request).getBody();
	}

	public CustomerAuthDTO changePassword(@Valid ChangePasswordRequest request) {
		return customerDataService.changePassword(request).getBody();
	}
	
}
