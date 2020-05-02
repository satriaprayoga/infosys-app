package com.infosys.message.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.infosys.message.MailService;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableBinding(RegistrationEventBinding.class)
@Slf4j
public class RegistrationEventListener {
	
	@Autowired
	private MailService mailService;
	
		
	public RegistrationEventListener(RegistrationEventBinding sink) {
	
	}
	
	@StreamListener(target = RegistrationEventBinding.INPUT)
	public void proccessRegistrationEvent(RegistrationEvent event) {
		log.info("######### receive event: "+event.getEmail()+", "+event.getKey()+", "+event.getConfirmUrl());
		switch (event.getType()) {
		case RegistrationEvent.ACTIVATION:
			mailService.sendRegistrationEmail(event);
			break;

		default:
			break;
		}
	}
	
	

}
