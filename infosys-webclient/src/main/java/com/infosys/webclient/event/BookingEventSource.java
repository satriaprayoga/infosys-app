package com.infosys.webclient.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookingEventSource {

	public static final String OUTPUT="bookingEventOutput";
	
	@Output(OUTPUT)
	MessageChannel bookingEvent();
}
