package com.infosys.search.event;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.elasticsearch.common.time.DateUtils;
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
	private BookedService bookedservice;

	
	@StreamListener(target = BookingEventBinding.INPUT)
	public void proccessBookingEvent(BookingEvent event) throws IOException {
		log.info("######### receive event search: "+event);
		
		Booked booked=new Booked();
		booked.setPackageId(event.getPackageId());
		booked.setBookDate(event.getBookedDate());
		booked.setCurrentCapacity(event.getCapacity()-event.getOrder());
		bookedservice.insert(booked);
	}
}
