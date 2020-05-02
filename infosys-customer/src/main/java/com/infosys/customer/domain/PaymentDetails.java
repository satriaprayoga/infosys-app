package com.infosys.customer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class PaymentDetails extends DomainAuditable{

	private static final long serialVersionUID = 6832627997375512540L;
	
	@Id
	private String id;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private Booking booking;
	
	private String paymentType;
	
	private String cardNumber;
	
	private String cvv;
	
	private String validUntil;
	
	private Long totalAmount;
	

}
