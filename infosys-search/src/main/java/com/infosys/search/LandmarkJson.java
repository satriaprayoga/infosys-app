package com.infosys.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LandmarkJson {

	
	private String landmark;
	
	private String description;

	private String destination;
	
	private String destinationCode;
	
	private Integer hour;
	
	private Integer minute;
}
