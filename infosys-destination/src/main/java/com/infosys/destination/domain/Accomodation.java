package com.infosys.destination.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Accomodation extends DomainAuditable{

	private static final long serialVersionUID = 522751772482979600L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	
	private String provider;
	
	@ManyToOne
	@JoinColumn(name = "destination_code")
	private Destination destination;
	
	@OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Room> rooms=new ArrayList<Room>();
	
	private String description;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String phone;
	
	public void addRoom(Room room) {
		room.setAccomodation(this);
		getRooms().add(room);
	}
	
	public void removeRoom(Room room) {
		room.setAccomodation(this);
		getRooms().remove(room);
	}

}
