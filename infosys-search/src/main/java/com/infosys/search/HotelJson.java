package com.infosys.search;

import lombok.Data;

@Data
public class HotelJson {

	
	private String destination;

	private String destinationId;
	
	
	private String location;
	
	//@Field(type = FieldType.Keyword)
	//private String [] landmarks;
	//@Field(type = FieldType.Keyword)
	//private String [] additionals;
	
	private String group;
	
	private String name;
	
	
	private boolean published=false;
	
	private String unit;
	
	
	private long price;
	
	
	private int minOrder;
	
	private int capacity;
}
