package com.infosys.customer.domain;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

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
	
	public static final int EXPIRATION = 60 * 24;
	
	@Id @GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid2", name = "uuid")
	private String id;
	
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
	
	private boolean enabled = true;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date activationExpired;
	
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	public boolean isProfileCompleted() {
		return (!phone.isEmpty() && !address.isEmpty() 
				&& !country.isEmpty() 
				&& !city.isEmpty() 
				&& !zipCode.isEmpty() && !state.isEmpty());
	}
	
	public Date calculateExpiredDate(int expInMinute) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expInMinute);
		return new Date(cal.getTime().getTime());
	}
}
