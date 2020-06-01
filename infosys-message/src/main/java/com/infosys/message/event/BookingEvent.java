package com.infosys.message.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEvent {
	private String name;
	private String email;
	private String billingAddress;
	private String packageName;
	private String packageGroup;
	private String destination;
	private String bankAccount;
	private String totalAmount;
	private String bookingCode;
	private String status;
	
	
}
