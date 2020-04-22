package com.infosys.data.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {

	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String location;
	
	private String destinationCode="random";
	
	
	private String company;
	
	private String email;
	
	private String phone;
}
