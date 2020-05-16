package com.infosys.search;

import lombok.Data;

@Data
public class TourJson {


	private String destination;
	
	
	private String destinationId;

	private String location;
	

	private String [] landmarks;
	//@Field(type = FieldType.Keyword)
	//private String [] additionals;

	private String group;
	
	private String name;

	private boolean accomodationIncluded;
	
	
	private boolean published;
	
	
	private String unit;
	

	private long price;
	

	private int minOrder;

	private int capacity;

	private int day;
	
	private int night;
}
