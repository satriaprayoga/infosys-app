package com.infosys.data.event;


import com.infosys.data.domain.Tour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourEvent {

	public static final String TOUR_CREATE="tourCreate";
	public static final String TOUR_UPDATE="tourUpdate";
	public static final String TOUR_DELETE="tourDelete";
	
	private String action;
	private Tour request;
}
