package com.infosys.destination.dto.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {

	
	private String id;
	
	private String name;
	
	private String description;
	
	private String location;
	
	private String destinationCode="random";
	
	private String company;
	
	private String email;
	
	private String phone;
}
