package com.infosys.customer.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infosys.customer.domain.Customer;

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
	
	public static CustomerDTO create(Customer customer) {
		CustomerDTO dto=new CustomerDTO();
		dto.setId(customer.getId());
		dto.setEmail(customer.getEmail());
		dto.setUsername(customer.getUsername());
		dto.setPassword(customer.getPassword());
		dto.setCountry(customer.getCountry());
		dto.setState(customer.getState());
		dto.setCity(customer.getCity());
		dto.setZipCode(customer.getZipCode());
		dto.setAddress(customer.getAddress());
		dto.setPhone(customer.getPhone());
		dto.setProviderId(customer.getProviderId());
		dto.setImageUrl(customer.getImageUrl());
		dto.setActivationKey(customer.getActivationKey());
		dto.setProvider(customer.getProvider().toString());
		return dto;
	}
}
