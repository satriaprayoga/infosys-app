package com.infosys.data.event;

import com.infosys.data.domain.Booking;

import lombok.Data;

@Data
public class BookingEvent {

	public static final String BOOKING_BOOKED="booked";
	public static final String BOOKING_CANCELED="canceled";
	public static final String BOOKING_PAID="paid";
	
	private String state;
	private Booking request;
}
