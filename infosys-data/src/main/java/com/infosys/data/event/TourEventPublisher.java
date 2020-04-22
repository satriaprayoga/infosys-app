package com.infosys.data.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(TourEventSource.class)
public class TourEventPublisher {
	
	private final TourEventSource source;
	
	public TourEventPublisher(TourEventSource source) {
		this.source=source;
	}

	public void publishTourEvent(TourEvent event) {
		MessageChannel channel=source.tourEvent();
		channel.send(MessageBuilder.withPayload(event).build());
	}
}
