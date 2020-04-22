package com.infosys.data.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.infosys.data.service.RandomUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Booking extends DomainAuditable{

	private static final long serialVersionUID = 4181992050876784204L;
	
	@Id
	private String id;
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private Tour tour;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDate checkin;
	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDate checkout;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Enumerated(EnumType.STRING)
	private BookingStatus status;
	
	@PrePersist
	public void generateId() {
		this.setId(RandomUtil.generateRandomAlphanumeric(8));
	}

}
