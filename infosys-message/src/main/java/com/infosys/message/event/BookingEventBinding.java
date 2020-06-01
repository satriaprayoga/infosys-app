package com.infosys.message.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BookingEventBinding {
	
	public static final String INPUT="bookingEventInput";
	
	@Input(INPUT)
	SubscribableChannel bookingEvent();

}
