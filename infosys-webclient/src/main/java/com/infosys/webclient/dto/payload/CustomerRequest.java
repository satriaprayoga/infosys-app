package com.infosys.webclient.dto.payload;

import lombok.Data;

@Data
public class CustomerRequest {


	private String email;

	private String username;

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
