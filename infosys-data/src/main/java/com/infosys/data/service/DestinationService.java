package com.infosys.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.infosys.data.domain.Accessability;
import com.infosys.data.domain.Destination;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.repository.AccessabilityRepository;
import com.infosys.data.repository.DestinationRepository;
import com.infosys.data.spec.DestinationSpecification;
import com.infosys.data.spec.SearchCriteria;
import com.infosys.data.spec.SearchOperation;

@Service
@Transactional
public class DestinationService extends AbstractBaseService<Destination, Long> {

	private final DestinationRepository destinationRepository;
	private final AccessabilityRepository accessabilityRepository;

	public DestinationService(DestinationRepository destinationRepository, AccessabilityRepository accessabilityRepository) {
		this.destinationRepository = destinationRepository;
		this.accessabilityRepository=accessabilityRepository;
		setRepository(destinationRepository);
	}

	
	public void deleteById(Long id) {
		destinationRepository.findOneById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", id.toString());
		});
		super.deleteById(id);
	}

	
	public List<Destination> findLocationLike(String locationString){
		DestinationSpecification location=new DestinationSpecification();
		location.add(new SearchCriteria("location", locationString, SearchOperation.MATCH));
		DestinationSpecification name=new DestinationSpecification();
		name.add(new SearchCriteria("name", locationString, SearchOperation.MATCH));
		return destinationRepository.findAll(Specification.where(location).or(name));
	}

	public Destination findOneById(Long id) {
		return destinationRepository.findOneById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", id.toString());
		});
	}

	public Destination findOneByCode(String code) {
		return destinationRepository.findOneByDestinationCode(code).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "code", code);
		});
	}
	
	public List<Accessability> getAccessabilities(Long destId){
		return destinationRepository.findById(destId).map((dest)->{
			return dest.getAccessabilities();
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", destId.toString());
		});
	}
	
	public Accessability addAccessability(Long destId,Accessability accessability) {
		return destinationRepository.findById(destId).map((dest)->{
			Accessability acc=new Accessability();
			acc.setAccess(accessability.getAccess());
			acc.setKm(accessability.getKm());
			acc.setMinute(accessability.getMinute());
			acc.setDestination(dest);
			dest.getAccessabilities().add(acc);
			destinationRepository.save(dest);
			return acc;
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", destId.toString());
		});
	}
	
	public Destination removeAccessability(Long destId,Long accessId) {
		return accessabilityRepository.findById(accessId).map((access)->{
			Destination dest=findOneById(destId);
			dest.getAccessabilities().remove(access);
			return destinationRepository.save(dest);
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", accessId.toString());
		});
		
	}


	@Override
	protected Destination fromRequest(Long id, Destination request) {
		Destination dest = null;
		if (id == null) {
			dest = new Destination();
			dest.setDestinationCode(RandomUtil.generateRandomAlphabet(4));
		} else {
			dest = findOne(id).get();
			//dest.setDestinationCode(request.getDestinationCode());
		}
		dest.setName(request.getName());
		dest.setCompany(request.getCompany());
		dest.setPhone(request.getPhone());
		dest.setEmail(request.getEmail());
		dest.setLocation(request.getLocation());
		dest.setDescription(request.getDescription());
		
		return dest;

	}
}
