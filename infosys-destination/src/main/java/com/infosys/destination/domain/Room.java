package com.infosys.destination.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Room extends DomainAuditable{

	private static final long serialVersionUID = 3869251201520822435L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Double discount;
	
	private Integer occupancy;
	
	private Integer available;
	
	@ManyToOne
	@JoinColumn(name = "accomodation_id")
	@JsonBackReference
	private Accomodation accomodation;

}
