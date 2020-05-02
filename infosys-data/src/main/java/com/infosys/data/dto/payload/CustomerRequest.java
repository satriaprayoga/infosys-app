package com.infosys.data.dto.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@NotNull
	private String country;
	@NotNull
	private String state;
	@NotNull
	private String city;
	@NotNull
	private String zipCode;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	
	private String providerId;
	
	private String imageUrl;
	
	private String activationKey;
	
	private String provider;
	
}
