package com.infosys.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.Destination;
import com.infosys.data.domain.Landmark;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.repository.DestinationRepository;
import com.infosys.data.repository.LandmarkRepository;

@Service
public class LandmarkService extends AbstractBaseService<Landmark, Long>{
	
	private final LandmarkRepository landmarkRepository;
	private final DestinationRepository destinationRepository;
	
	public LandmarkService(LandmarkRepository landmarkRepository,DestinationRepository destinationRepository) {
		this.landmarkRepository=landmarkRepository;
		this.destinationRepository=destinationRepository;
		setRepository(landmarkRepository);
	}
	
	public List<Landmark> findByDestination(Long destId){
		return destinationRepository.findById(destId).map((destination)->{
			return landmarkRepository.findByDestination(destination);
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", destId.toString());
		});
		
	}
	
	public Landmark create(Long destId, Landmark landmark) {
		return destinationRepository.findById(destId).map((destination)->{
			landmark.setDestination(destination);
			return landmarkRepository.save(landmark);
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", destId.toString());
		});
	}

	@Override
	protected Landmark fromRequest(Long id, Landmark request) {
		Landmark landmark=null;
		if(id==null) {
			landmark=new Landmark();
		}else {
			landmark=findOne(id).get();
		}
		landmark.setName(request.getName());
		landmark.setDescription(request.getDescription());
		landmark.setDestination(request.getDestination());
		landmark.setHour(request.getHour());
		landmark.setMinute(request.getMinute());
		return landmark;
	}

}
