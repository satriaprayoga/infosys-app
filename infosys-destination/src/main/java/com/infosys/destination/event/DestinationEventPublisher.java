package com.infosys.destination.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.infosys.destination.domain.Destination;

@Component
@EnableBinding(DestinationEventSource.class)
public class DestinationEventPublisher {

	private final DestinationEventSource source;
	private final EventMapper eventMapper;
	
	public DestinationEventPublisher(DestinationEventSource source,EventMapper eventMapper) {
		this.source=source;
		this.eventMapper=eventMapper;
	}
	
	public void publishEvent(String eventType,Destination destination) {
		DestinationEvent event=eventMapper.mapEvent(eventType, destination);
		MessageChannel channel=source.destinationEvent();
		channel.send(MessageBuilder.withPayload(event).build());
	}
}
