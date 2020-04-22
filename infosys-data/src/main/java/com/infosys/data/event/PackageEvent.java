package com.infosys.data.event;

import com.infosys.data.domain.Additional;
import com.infosys.data.domain.Landmark;
import com.infosys.data.domain.TourPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageEvent {
	
	public static final String TOUR_CREATE="tourCreate";
	public static final String TOUR_UPDATE="tourUpdate";
	public static final String TOUR_DELETE="tourDelete";
	
	private String action;
	
	private Long id;

	
	private String destination;
	
	private String destinationId;
	
	
	private String location;
	
	
	private String [] landmarks;
	
	private String [] additionals;
	
	private String [] activities;
	
	private String tourGroup;
	
	private String tourName;

	private boolean accomodationIncluded;
	
	
	private boolean published;
	
	
	private String unit;
	
	
	private long price;
	
	
	private int minOrder;
	
	private int capacity;
	
	private int day;
	private int night;
	
	public static PackageEvent fromPackage(String action,TourPackage pkg) {
		PackageEvent event=new PackageEvent();
		event.setAction(action);
		event.setId(pkg.getId());
		event.setTourName(pkg.getTourName());
		event.setTourGroup(pkg.getTourGroup().getName());
		event.setDestination(pkg.getTourGroup().getDestination().getName());
		event.setLocation(pkg.getTourGroup().getDestination().getLocation());
		event.setLandmarks(pkg.getLandmarks().stream().map(Landmark::getName).toArray(String[]::new));
		event.setAdditionals(pkg.getAdditionals().stream().map(Additional::getName).toArray(String[]::new));
		event.setAccomodationIncluded(pkg.isAccomodationIncluded());
		event.setPublished(pkg.isPublished());
		event.setCapacity(pkg.getCapacity());
		event.setDay(pkg.getDay());
		event.setMinOrder(pkg.getMinOrder());
		event.setNight(pkg.getNight());
		event.setUnit(pkg.getUnit());
		event.setPublished(pkg.isPublished());
		event.setDestinationId(pkg.getTourGroup().getDestination().getId().toString());
		return event;
	}

}
