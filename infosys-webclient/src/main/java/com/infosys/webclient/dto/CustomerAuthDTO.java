package com.infosys.webclient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerAuthDTO implements Serializable{

	private static final long serialVersionUID = 1L;


	private String id;
	private String email;
	private String username;
	private String password;
	private boolean enabled;
	
	
}
