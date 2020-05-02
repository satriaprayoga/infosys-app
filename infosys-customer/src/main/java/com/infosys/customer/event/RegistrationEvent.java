package com.infosys.customer.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationEvent{

	private String id;
	private String email;
	private String key;
	private String confirmUrl;
	
	public RegistrationEvent(String id,String email,String key,String confirmUrl) {
		this.id=id;
		this.key=key;
		this.email=email;
		this.confirmUrl=confirmUrl;
	}


}
