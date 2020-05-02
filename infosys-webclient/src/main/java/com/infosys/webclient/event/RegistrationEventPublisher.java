package com.infosys.webclient.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(RegistrationEventSource.class)
public class RegistrationEventPublisher {

	private final RegistrationEventSource source;
	
	public RegistrationEventPublisher(RegistrationEventSource source) {
		this.source=source;
	}
	
	public void publishRegistrationEvent(RegistrationEvent event) {
		MessageChannel channel=source.registrationEvent();
		channel.send(MessageBuilder.withPayload(event).build());
	}
}
