package com.infosys.customer.dto.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
public class BookingRequest {
	
	private String customerId;

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
	
	private Integer adults;
	
	private Integer children;
	
	private Integer day;
	
	private Integer night;
	
	private Long grossAmount;
	
	private String status;
}
