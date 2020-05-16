package com.infosys.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infosys.search.domain.Landmark;
import com.infosys.search.exceptions.ResourceNotFoundException;
import com.infosys.search.repository.LandmarkRepository;

@Service
public class LandmarkService extends AbstractBaseService<Landmark, String>{

	private final LandmarkRepository landmarkRepository;
	
	public LandmarkService(LandmarkRepository  landmarkRepository) {
		this.landmarkRepository=landmarkRepository;
		setRepository(landmarkRepository);
	}
	
	public Landmark findOneById(String id) {
		return landmarkRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException("Landmark with id: "+id, "not found");
		});
	}
	
	public List<Landmark> findByDestinationCode(String destinationCode){
		return landmarkRepository.findByDestinationCode(destinationCode);
	}
}
