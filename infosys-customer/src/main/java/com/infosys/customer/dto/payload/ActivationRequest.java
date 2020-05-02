package com.infosys.customer.dto.payload;

import lombok.Data;

@Data
public class ActivationRequest {

	private String id;
	private String key;
	private String confirmUrl="";
}
