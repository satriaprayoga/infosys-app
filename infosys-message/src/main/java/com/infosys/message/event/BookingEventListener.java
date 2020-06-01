package com.infosys.message.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.infosys.message.MailService;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableBinding(BookingEventBinding.class)
@Slf4j
public class BookingEventListener {

	@Autowired
	private MailService mailService;
	
	@StreamListener(target = BookingEventBinding.INPUT)
	public void proccessBookingEvent(BookingEvent event) {
		log.info("######### receive event: "+event);
		mailService.sendInvoiceEmail(event);
	}
}
