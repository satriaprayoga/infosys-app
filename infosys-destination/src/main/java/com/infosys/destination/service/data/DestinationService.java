package com.infosys.destination.service.data;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.infosys.destination.domain.Accessability;
import com.infosys.destination.domain.Destination;
import com.infosys.destination.exception.ResourceNotFoundException;
import com.infosys.destination.repository.AccessabilityRepository;
import com.infosys.destination.repository.DestinationRepository;
import com.infosys.destination.service.AbstractBaseService;
import com.infosys.destination.service.DestinationSpecification;
import com.infosys.destination.service.SearchCriteria;
import com.infosys.destination.service.SearchOperation;

public class DestinationService extends AbstractBaseService<Destination, String>{

	private final DestinationRepository destinationRepository;
	private final AccessabilityRepository accessabilityRepository;

	public DestinationService(DestinationRepository destinationRepository, AccessabilityRepository accessabilityRepository) {
		this.destinationRepository = destinationRepository;
		this.accessabilityRepository=accessabilityRepository;
		setRepository(destinationRepository);
	}
	
	public Destination findOneById(String id) {
		return destinationRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id: "+id.toString());
		});
	}

	public Destination findOneByCode(String code) {
		return destinationRepository.findOneByDestinationCode(code).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "code: "+code);
		});
	}
	
	public List<Destination> findLocationLike(String locationString){
		DestinationSpecification location=new DestinationSpecification();
		location.add(new SearchCriteria("location", locationString, SearchOperation.MATCH));
		DestinationSpecification name=new DestinationSpecification();
		name.add(new SearchCriteria("name", locationString, SearchOperation.MATCH));
		return destinationRepository.findAll(Specification.where(location).or(name));
	}
	
	public List<Accessability> getAccessabilities(String destId){
		return destinationRepository.findById(destId).map((dest)->{
			return dest.getAccessabilities();
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id: "+ destId.toString());
		});
	}
	
	public Accessability addAccessability(String destId,Accessability accessability) {
		return destinationRepository.findById(destId).map((dest)->{
			Accessability acc=new Accessability();
			acc.setAccess(accessability.getAccess());
			acc.setKm(accessability.getKm());
			acc.setMinute(accessability.getMinute());
			acc.setDestination(dest);
			dest.getAccessabilities().add(acc);
			destinationRepository.save(dest);
			return acc;
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id  "+destId);
		});
	}
	
	public Destination removeAccessability(String destId,String accessId) {
		return accessabilityRepository.findById(accessId).map((access)->{
			Destination dest=findOneById(destId);
			dest.getAccessabilities().remove(access);
			return destinationRepository.save(dest);
		}).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id:"+ accessId);
		});
		
	}
	
	public void deleteById(String id) {
		destinationRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id:"+ id);
		});
		super.deleteById(id);
	}
	
	
}
