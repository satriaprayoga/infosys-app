package com.infosys.customer.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "booking",uniqueConstraints = {
		@UniqueConstraint(columnNames = "code")
})
public class Booking extends DomainAuditable{
	private static final long serialVersionUID = 4181992050876784204L;
	
	@Id @GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid2", name = "uuid")
	private String id;
	
	@NotNull
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private String destination;
	
	private String packageGroup;
	
	private String packageId;
	
	private String packageName;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate checkin;
	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate checkout;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String billingAddress;
	
	private Integer adults;
	
	private Integer children;
	
	private Integer day;
	
	private Integer night;
	
	private Long grossAmount;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus status;
		
	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private PaymentDetails paymentDetails;

}
