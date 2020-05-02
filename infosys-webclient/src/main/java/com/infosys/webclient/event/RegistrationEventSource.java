package com.infosys.webclient.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RegistrationEventSource {

	public static final String OUTPUT="registrationEventOutput";
	
	@Output(OUTPUT)
	MessageChannel registrationEvent();
}
