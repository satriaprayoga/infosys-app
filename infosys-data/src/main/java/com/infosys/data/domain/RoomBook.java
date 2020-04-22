package com.infosys.data.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class RoomBook extends DomainAuditable{

	
	private static final long serialVersionUID = 5204385329920094836L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd-MM-YYY HH:mm")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDateTime bookingTime;
	
	@JsonFormat(pattern = "dd-MM-YYY")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDate checkIn;
	@JsonFormat(pattern = "dd-MM-YYY")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDate checkOut;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Accomodation hotel;
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;

}
