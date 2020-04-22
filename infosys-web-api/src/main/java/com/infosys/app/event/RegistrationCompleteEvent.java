package com.infosys.app.event;

import org.springframework.context.ApplicationEvent;

import com.infosys.app.domain.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationCompleteEvent extends ApplicationEvent{

	private static final long serialVersionUID = 6756778059605194289L;
	
	private User user;
	private String appUrl;
	
	public RegistrationCompleteEvent(Object source) {
		super(source);
	}
	
	public RegistrationCompleteEvent(Object source,String appUrl) {
		super(source);
		this.appUrl=appUrl;
	}
	
	
	
}
