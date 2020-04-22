package com.infosys.data.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.infosys.data.service.TourPackageService;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableBinding(TourEventSink.class)
public class TourEventListener {
	
	private final TourPackageService tourService;
	
	public TourEventListener(TourPackageService tourService) {
		this.tourService=tourService;
	}

	@StreamListener(target = TourEventSink.INPUT)
	public void processTourEvent(TourEvent event) {
		
		String action=event.getAction();
		switch (action) {
		case TourEvent.TOUR_CREATE:
			tourService.create(event.getRequest());
			break;
		case TourEvent.TOUR_UPDATE:
			
			
			break;
		case TourEvent.TOUR_DELETE:
			tourService.deleteById(event.getRequest().getId());
			break;
		default:
			break;
		}
	}
}
