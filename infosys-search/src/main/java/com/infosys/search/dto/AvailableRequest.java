package com.infosys.search.dto;

import lombok.Data;

@Data
public class AvailableRequest {
	private String packageId;
	
	private int max;
	
	private String checkIn;
}
