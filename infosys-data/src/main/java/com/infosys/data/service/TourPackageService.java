package com.infosys.data.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.Tour;
import com.infosys.data.repository.TourRepository;

@Service
public class TourPackageService extends AbstractBaseService<Tour, String>{
	
	private final TourRepository tourRepository;
	
	

	public TourPackageService(TourRepository tourRepository) {
		this.tourRepository = tourRepository;
		setRepository(tourRepository);
	}
	
	public Boolean hasPromo(String id) {
		return false;
	}



	@Override
	protected Tour fromRequest(String id, Tour request) {
		Tour tour=null;
		if(id==null) {
			tour= new Tour();
			tour.setId(UUID.randomUUID().toString());
		}else {
			tour=tourRepository.getOne(id);
			
		}
		tour.setAccomodationIncluded(request.isAccomodationIncluded());
		tour.setDestination(request.getDestination());
		tour.setDestinationId(request.getDestinationId());
		tour.setLandmarks(request.getLandmarks());
		tour.setAdditionals(request.getAdditionals());
		tour.setLocation(request.getLocation());
		tour.setMinOrder(request.getMinOrder());
		tour.setPrice(request.getPrice());
		tour.setPublished(request.isPublished());
		tour.setTourGroup(request.getTourGroup());
		tour.setTourName(request.getTourName());
		tour.setUnit(request.getUnit());
	
		return tour;
	}

}
