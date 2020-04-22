package com.infosys.data.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TourEventSource {

	public static final String OUTPUT="tourEventOutput";
	
	@Output(OUTPUT)
	MessageChannel tourEvent();
}
