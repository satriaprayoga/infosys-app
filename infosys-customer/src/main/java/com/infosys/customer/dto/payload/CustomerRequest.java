package com.infosys.customer.dto.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerRequest {

	@NotNull
	@Email
	private String email;
	@NotNull
	private String username;
	@NotNull
	private String password;

	private String country;
	
	private String state;

	private String city;
	
	private String zipCode;
	
	private String address;
	
	private String phone;
	
	private String providerId;
	
	private String imageUrl;
	
	private String activationKey;
	
	private String provider;
	
	private String activationUrl;
	
}
