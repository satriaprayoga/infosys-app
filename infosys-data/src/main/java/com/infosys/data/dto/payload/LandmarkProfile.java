package com.infosys.data.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandmarkProfile {

	private Long id;
	private String name;
	private String description;
	private Integer hour;
	private Integer minute;
	private String destination;
	private String location;
	private Long destinationId;
	
}
