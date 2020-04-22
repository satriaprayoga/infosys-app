package com.infosys.oauth.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends DomainAuditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1401849391161879059L;
	
	private String id;
	
	@NotNull
	@Email
	private String email;
	@NotNull
	private String username;
	@NotNull
	@JsonIgnore
	private String password;
	
	private String address;
	
	private String phone;
	
	
	private boolean enabled = true;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;
	
	private String provider;

}
