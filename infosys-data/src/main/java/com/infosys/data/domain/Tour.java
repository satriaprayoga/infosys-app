package com.infosys.data.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tour extends DomainAuditable{
	private static final long serialVersionUID = -1151972542192249320L;
	
	@Id 
	private String id;

	
	private String destination;
	
	private String destinationId;
	
	
	private String location;
	
	
	private String [] landmarks;
	
	private String [] additionals;
	
	private String tourGroup;
	
	private String tourName;

	private boolean accomodationIncluded;
	
	
	private boolean published;
	
	
	private String unit;
	
	
	private long price;
	
	
	private int minOrder;
	
	private int capacity;
	
	private int day;
	private int night;

}
