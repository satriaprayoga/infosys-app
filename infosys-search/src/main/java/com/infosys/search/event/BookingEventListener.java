package com.infosys.search.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.infosys.search.domain.Booked;
import com.infosys.search.service.BookedService;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableBinding(BookingEventBinding.class)
@Slf4j
public class BookingEventListener {

	@Autowired
	private BookedService bookedService;
	
	@StreamListener(target = BookingEventBinding.INPUT)
	public void proccessBookingEvent(BookingEvent event) {
		log.info("######### receive event: "+event);
		Booked booked=new Booked();
		booked.setId(UUID.randomUUID().toString());
		booked.setCurrentCapacity(event.getCapacity()-event.getQuantity());
		booked.setBookDate(event.getBookedDate());
		bookedService.create(booked);
	}
}
