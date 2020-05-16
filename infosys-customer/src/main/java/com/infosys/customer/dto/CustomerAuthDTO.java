package com.infosys.customer.dto;

import java.io.Serializable;

import com.infosys.customer.domain.Customer;

import lombok.Data;

@Data
public class CustomerAuthDTO implements Serializable{

	private static final long serialVersionUID = 1L;


	private String id;
	private String email;
	private String username;
	private String password;
	private boolean enabled;
	private String activationKey;
	
	public static CustomerAuthDTO create(Customer c) {
		CustomerAuthDTO dto=new CustomerAuthDTO();
		dto.setId(c.getId());
		dto.setEmail(c.getEmail());
		dto.setUsername(c.getUsername());
		dto.setPassword(c.getPassword());
		dto.setEnabled(c.isEnabled());
		dto.setActivationKey(c.getActivationKey());
		return dto;
	}
}
