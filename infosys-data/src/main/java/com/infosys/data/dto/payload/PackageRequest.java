package com.infosys.data.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PackageRequest {

	private Long id;
	
	private String tourName;
	
	Long tourGroupId;
	private boolean accomodationIncluded;
	
	
	private boolean published;
	
	
	private String unit;
	
	
	private long price;
	
	
	private int minOrder;
	
	private int capacity;
	
	private int day;
	private int night;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class EditAdditionalRequest{
		private Long additionalId;
		private Long packageId;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class EditLandmarkRequest{
		private Long landmarkId;
		private Long packageId;
	}
}
