package com.infosys.message.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationEvent{
	
	public final static String ACTIVATION="ACTIVATION";
	public final static String RESET_PASSWORD="RESET_PASSWORD";

	private String id;
	private String name;
	private String email;
	private String key;
	private String confirmUrl;
	private String type;
	
	public RegistrationEvent(String id,String email,String key,String confirmUrl) {
		this.id=id;
		this.key=key;
		this.email=email;
		this.confirmUrl=confirmUrl;
	}


}
