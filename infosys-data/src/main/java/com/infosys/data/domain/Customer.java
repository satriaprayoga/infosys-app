package com.infosys.data.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infosys.data.service.RandomUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Customer extends DomainAuditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1401849391161879059L;
	
	@Id
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
	
	@PrePersist
	public void generateId() {
		this.setId(RandomUtil.generateRandomAlphanumeric(8));
	}

}
