package com.infosys.webclient.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(BookingEventSource.class)
public class BookingEventPublisher {

	private final BookingEventSource bookingEventSource;
	
	public BookingEventPublisher(BookingEventSource bookingEventSource) {
		this.bookingEventSource=bookingEventSource;
	}
	
	public void publishBookingEvent(BookingEvent bookingEvent) {
		MessageChannel channel=bookingEventSource.bookingEvent();
		channel.send(MessageBuilder.withPayload(bookingEvent).build());
	}
}
