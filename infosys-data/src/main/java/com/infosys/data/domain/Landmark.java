package com.infosys.data.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity	
public class Landmark extends DomainAuditable{

	
	private static final long serialVersionUID = 2299511932653949846L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "destination_id")
	private Destination destination;
	
	private Integer hour;
	
	private Integer minute;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "landmarks", cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	private Set<TourPackage> packages=new HashSet<>();
	
	

}
