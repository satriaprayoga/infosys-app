package com.infosys.webclient.dto.payload;

import lombok.Data;

@Data
public class ActivationRequest {

	private String id;
	private String key;
	private String confirmUrl="";
}
