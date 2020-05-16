package com.infosys.destination.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class TourGroup extends DomainAuditable{

	private static final long serialVersionUID = 7851357143421854495L;

	@Id @GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid2", name = "uuid")
	private String id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "destination_id")
	private Destination destination;
	
}
