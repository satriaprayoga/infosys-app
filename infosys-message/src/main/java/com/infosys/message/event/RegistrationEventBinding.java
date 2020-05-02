package com.infosys.message.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RegistrationEventBinding {

	public static final String INPUT="registrationEventInput";
	
	@Input(INPUT)
	SubscribableChannel registrationEvent();
}
