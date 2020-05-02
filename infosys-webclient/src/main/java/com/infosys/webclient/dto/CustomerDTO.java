package com.infosys.webclient.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CustomerDTO implements Serializable{

	private static final long serialVersionUID = -2319485722935012669L;
	
	private String id;


	private String email;
	
	private String username;
	@JsonIgnore
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
	
	
}
