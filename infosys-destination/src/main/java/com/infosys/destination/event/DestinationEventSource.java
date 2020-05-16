package com.infosys.destination.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DestinationEventSource {

	public static final String OUTPUT="destinationEventOutput";
	
	@Output(OUTPUT)
	MessageChannel destinationEvent();
}
