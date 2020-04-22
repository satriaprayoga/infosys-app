package com.infosys.data.event;

import com.infosys.data.service.BookingService;

public class BookingEventListener {

	private final BookingService bookingService;
	
	public BookingEventListener(BookingService bookingService) {
		this.bookingService=bookingService;
	}
	
	public void processBookingEvent(BookingEvent event) {
		switch (event.getState()) {
		case BookingEvent.BOOKING_BOOKED:
			
			break;

		default:
			break;
		}
	}

}
