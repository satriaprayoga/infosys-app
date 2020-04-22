package com.infosys.data.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface TourEventSink {

public static final String INPUT="tourEventInput";
	
	@Output(INPUT)
	SubscribableChannel tourEvent();
}
