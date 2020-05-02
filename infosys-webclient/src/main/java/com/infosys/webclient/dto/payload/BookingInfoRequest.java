package com.infosys.webclient.dto.payload;

import lombok.Data;

@Data
public class BookingInfoRequest {
	
	private String bookingCode;

	private String name;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String billingAddress;
}
