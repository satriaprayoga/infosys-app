package com.infosys.data.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity	
public class Destination extends DomainAuditable {
	
	private static final long serialVersionUID = -5979035120751303803L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	
	private String description;
	@NotNull
	private String location;
	@NotNull
	@NaturalId
	@Column(name = "destination_code", unique = true)
	private String destinationCode="random";
	
	
	private String company;
	
	private String email;
	
	private String phone;
	
	@OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Accessability> accessabilities=new ArrayList<>();
	

}
