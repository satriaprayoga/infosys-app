package com.infosys.customer.dto;

import java.io.Serializable;

import com.infosys.customer.domain.Customer;

import lombok.Data;

@Data
public class CustomerActivationDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String email;
	private String username;
	private String activationKey;
	private boolean enabled;
	
	public static CustomerActivationDTO create(Customer c) {
		CustomerActivationDTO dto=new CustomerActivationDTO();
		dto.setId(c.getId());
		dto.setEmail(c.getEmail());
		dto.setUsername(c.getUsername());
		dto.setActivationKey(c.getActivationKey());
		dto.setEnabled(c.isEnabled());
		return dto;
	}
}
