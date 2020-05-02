package com.infosys.data.dto.payload;

import lombok.Data;

@Data
public class PaymentInfoRequest {
	
	private String bookingCode;

	private String paymentType;
	
	private String cardNumber;
	
	private String cvv;
	
	private String validUntil;
	
	private Long totalAmount;
}
