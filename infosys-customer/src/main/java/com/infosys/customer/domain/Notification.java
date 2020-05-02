package com.infosys.customer.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Notification extends DomainAuditable{

	private static final long serialVersionUID = 1296534355719040119L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String code;
	@NotNull
	private String message;
	@NotNull
	private String topic;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;
	@JsonFormat(pattern = "dd-MM-YYY HH:mm")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDateTime receivedTime;
	
	private boolean read=false;

}
