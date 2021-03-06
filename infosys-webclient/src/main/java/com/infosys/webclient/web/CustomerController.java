package com.infosys.webclient.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.webclient.dto.ApiResponse;
import com.infosys.webclient.dto.CustomerAuthDTO;
import com.infosys.webclient.dto.payload.ChangePasswordRequest;
import com.infosys.webclient.dto.payload.CustomerInfoRequest;
import com.infosys.webclient.security.CurrentUser;
import com.infosys.webclient.security.UserPrincipal;
import com.infosys.webclient.service.BookingService;
import com.infosys.webclient.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CustomerController() {
	}
	
	@GetMapping("/me")
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse getCustomerInfo(@CurrentUser UserPrincipal principal) {
		CustomerAuthDTO dto=new CustomerAuthDTO();
		dto.setId(principal.getId());
		dto.setUsername(principal.getUsername());
		dto.setEmail(principal.getEmail());
		dto.setEnabled(principal.isEnabled());
		return new ApiResponse(HttpStatus.OK, dto);
	}
	
	@PutMapping("/me")
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse updateCustomerInfo(@CurrentUser UserPrincipal principal, @Valid @RequestBody CustomerInfoRequest request) {
		return new ApiResponse(HttpStatus.OK, customerService.updateCustomerInfo(principal.getId(), request));
	}
	
	@PutMapping("/changePassword")
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse changePassword(@CurrentUser UserPrincipal principal, @Valid @RequestBody ChangePasswordRequest request) {
		String encodedPassword=passwordEncoder.encode(request.getPassword());
		ChangePasswordRequest encodeRequest=new ChangePasswordRequest();
		encodeRequest.setId(request.getId());
		encodeRequest.setPassword(encodedPassword);
		CustomerAuthDTO dto=customerService.changePassword(encodeRequest);
		return new ApiResponse(HttpStatus.OK, dto);
	}
	
	@GetMapping("/profileCompleted")
	@PreAuthorize("#principal.email == authentication.name")
	public ApiResponse isProfileCompleted(@CurrentUser UserPrincipal principal) {
		String id=principal.getId();
		return new ApiResponse(HttpStatus.OK, customerService.isProfileCompleted(id));
	}
	
	@GetMapping("/booking/{bcode}")
	@PreAuthorize("#principal.email == authentication.name")
    public ApiResponse findByCustomerAndCode(@CurrentUser UserPrincipal principal, @PathVariable String bcode) {
    	return new ApiResponse(HttpStatus.OK, bookingService.findByCode(bcode));
    }
	
}
