package com.infosys.destination.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image extends DomainAuditable{
	private static final long serialVersionUID = 3140800076989901270L;

	@Id @GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid2", name = "uuid")
	private String id;
	
	@NotNull
	private String filename;
	
	private String type;
	
	@Lob
	private byte[] data;
	
	private boolean cover=false;
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ImageCategory imageCategory;
	
	private Long refId;
	
	public Image(String filename,String type, byte [] data) {
		this.filename=filename;
		this.type=type;
		this.data=data;
	}
}
