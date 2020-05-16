package com.infosys.destination.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Additional extends DomainAuditable{
	private static final long serialVersionUID = -3040215900675475636L;
	
	@Id @GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid2", name = "uuid")
	private String id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "additionals", cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	private Set<TourPackage> packages=new HashSet<>();

}
