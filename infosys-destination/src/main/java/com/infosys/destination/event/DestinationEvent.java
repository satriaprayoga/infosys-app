package com.infosys.destination.event;

import lombok.Data;

@Data
public class DestinationEvent {

	private String eventType;
	
	private String id;
	private String destination;
	
	private String location;
	private String description;

	private String code;
	
	private String company;
	
	private String email;
	
	private String phone;
	
	
	private String [] accessabilities;
}
