package com.infosys.data.dto.payload;

import javax.validation.constraints.NotNull;

import com.infosys.data.domain.Landmark;

import lombok.Data;

@Data
public class LandmarkRequest {

	private Long id;
	private String name;
	private String description;
	private Integer hour;
	private Integer minute;
	private Long destId;
	
	public static Landmark toLandmark(LandmarkRequest request) {
		Landmark landmark=new Landmark();
		landmark.setName(request.getName());
		landmark.setDescription(request.getDescription());
		landmark.setHour(request.getHour());
		landmark.setMinute(request.getMinute());
		return landmark;
	}
	
}
