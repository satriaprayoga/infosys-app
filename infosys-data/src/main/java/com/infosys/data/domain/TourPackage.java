package com.infosys.data.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TourPackage extends DomainAuditable{

	private static final long serialVersionUID = 4719411848188453288L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private Long id;
	
	private String tourName;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
		@JoinTable(name = "package_landmark",
		    joinColumns = @JoinColumn(name = "landmark_id"),
		    inverseJoinColumns = @JoinColumn(name = "package_id")
		)
	private Set<Landmark> landmarks=new HashSet<>();
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
		@JoinTable(name = "package_additional",
		    joinColumns = @JoinColumn(name = "additional_id"),
		    inverseJoinColumns = @JoinColumn(name = "package_id")
		)
	private Set<Additional> additionals=new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "tour_group_id")
	private TourGroup tourGroup;
	
	private boolean accomodationIncluded;
	
	
	private boolean published;
	
	
	private String unit;
	
	
	private long price;
	
	
	private int minOrder;
	
	private int capacity;
	
	private int day;
	private int night;
	
	public void addLandmark(Landmark landmark) {
		this.getLandmarks().add(landmark);
		landmark.getPackages().add(this);
	}
	
	public void deleteLandmark(Landmark landmark) {
		this.getLandmarks().remove(landmark);
		landmark.getPackages().remove(this);
	}
	
	public void addAdditional(Additional additional) {
		this.getAdditionals().add(additional);
		additional.getPackages().add(this);
	}
	
	public void removeAdditional(Additional additional) {
		this.getAdditionals().remove(additional);
		additional.getPackages().remove(this);
	}

	
}
