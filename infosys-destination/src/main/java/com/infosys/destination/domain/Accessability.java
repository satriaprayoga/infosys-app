package com.infosys.destination.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Accessability extends DomainAuditable{

	private static final long serialVersionUID = -4539461929902414266L;
	
	@Id @GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid2", name = "uuid")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "destination_id")
	@JsonBackReference
	private Destination destination;
	
	private String access;
	
	private int km;
	
	private int minute;

}
