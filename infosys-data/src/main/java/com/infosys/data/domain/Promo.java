package com.infosys.data.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Promo extends DomainAuditable{

	private static final long serialVersionUID = 7797221230598480658L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double percentage;
	
	private String promoCode;
	
	private LocalDate start;
	
	private LocalDate finish;
	
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private Tour tour;
}
