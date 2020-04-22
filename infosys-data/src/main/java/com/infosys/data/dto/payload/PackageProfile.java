package com.infosys.data.dto.payload;

import java.util.ArrayList;
import java.util.List;

import com.infosys.data.domain.TourPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageProfile {
	
	private Long id;
	private String destination;
	private String location;
	private String tourName;
	private String tourGroup;
	private boolean accomodationIncluded;
	private boolean published;
	private String unit;
	private long price;
	private int minOrder;
	private int capacity;
	private int day;
	private int night;
	private List<LandmarkItem> landmarks=new ArrayList<>();
	private List<AdditionalItem> additionals=new ArrayList<>();
	
	public static PackageProfile fromPackage(TourPackage p) {
		return new PackageProfile(p.getId(), p.getTourGroup().getDestination().getName(), p.getTourGroup().getDestination().getLocation(), p.getTourName(), p.getTourGroup().getName(), p.isAccomodationIncluded(), p.isPublished(), p.getUnit(), p.getPrice(), p.getMinOrder(), p.getCapacity(), p.getDay(), p.getNight(), null, null);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class LandmarkItem{
		private Long id;
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AdditionalItem{
		private Long id;
		private String name;
	}

}
