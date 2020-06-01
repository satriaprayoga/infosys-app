package com.infosys.customer.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.customer.dto.CustomerActivationDTO;
import com.infosys.customer.dto.CustomerAuthDTO;
import com.infosys.customer.dto.CustomerDTO;
import com.infosys.customer.dto.payload.ActivationRequest;
import com.infosys.customer.dto.payload.ChangePasswordRequest;
import com.infosys.customer.dto.payload.CustomerInfoRequest;
import com.infosys.customer.dto.payload.CustomerRequest;
import com.infosys.customer.exceptions.ResourceNotFoundException;
import com.infosys.customer.service.data.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<CustomerDTO> register(@Valid @RequestBody CustomerRequest request) {
		//skip validation
		/*
		 * if(customerService.existsByEmail(request.getEmail())) { throw new
		 * UserAlreadyExistsException("User already exist"); }
		 */
		ResponseEntity<CustomerDTO> customer=new ResponseEntity<CustomerDTO>(CustomerDTO.create(customerService.registerNew(request)), HttpStatus.OK);
		return customer;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerDTO> updateCustomerInfo(@PathVariable String id, @Valid @RequestBody CustomerInfoRequest request) throws ResourceNotFoundException{
		ResponseEntity<CustomerDTO> customer=new ResponseEntity<CustomerDTO>(CustomerDTO.create(customerService.updateUserInfo(id, request)), HttpStatus.OK);
		return customer;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerAuthDTO> loadById(@PathVariable String id) throws ResourceNotFoundException{
		ResponseEntity<CustomerAuthDTO> customer=new ResponseEntity<CustomerAuthDTO>(CustomerAuthDTO.create(customerService.findById(id)), HttpStatus.OK);
		return customer;
	}
	
	@GetMapping("/email")
	public ResponseEntity<CustomerAuthDTO> loadByEmail(@RequestParam String email) throws ResourceNotFoundException{
		ResponseEntity<CustomerAuthDTO> customer=new ResponseEntity<CustomerAuthDTO>(CustomerAuthDTO.create(customerService.findByEmail(email)), HttpStatus.OK);
		return customer;
	}
	@GetMapping("/phone")
	public ResponseEntity<CustomerAuthDTO> loadByPhone(@RequestParam String phone) throws ResourceNotFoundException{
		ResponseEntity<CustomerAuthDTO> customer=new ResponseEntity<CustomerAuthDTO>(CustomerAuthDTO.create(customerService.findByPhone(phone)), HttpStatus.OK);
		return customer;
	}
	
	@PutMapping("/activate")
	public ResponseEntity<CustomerActivationDTO> activate(@Valid @RequestBody ActivationRequest request) {
		ResponseEntity<CustomerActivationDTO> act=new ResponseEntity<CustomerActivationDTO>(CustomerActivationDTO.create(customerService.activateCustomer(request.getId(),request.getKey())), HttpStatus.OK);
		return act;
	}
	@PutMapping("/changePassword")
	public ResponseEntity<CustomerAuthDTO> changePassword(@Valid @RequestBody ChangePasswordRequest request){
		ResponseEntity<CustomerAuthDTO> dto=new ResponseEntity<CustomerAuthDTO>(CustomerAuthDTO.create(customerService.changePassword(request.getId(), request.getPassword())), HttpStatus.OK);
		return dto;
	}
	
	@GetMapping("/email/exists")
	public ResponseEntity<Boolean> existByEmail(@RequestParam String email) {
		return ResponseEntity.ok(customerService.existsByEmail(email));
	}
	
	@GetMapping("/{id}/expired")
	public ResponseEntity<Boolean> isActivationExpired(@PathVariable String id){
		return new ResponseEntity<Boolean>(customerService.isActivationExipred(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/profileCompleted")
	public ResponseEntity<Boolean> isProfileCompleted(@PathVariable String id){
		return new ResponseEntity<Boolean>(customerService.isProfileCompleted(id), HttpStatus.OK);
	}
}
