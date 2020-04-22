package com.infosys.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.Accomodation;
import com.infosys.data.domain.Destination;
import com.infosys.data.dto.errors.ResourceConstraintException;
import com.infosys.data.repository.AccomodationRepository;

@Service
@Transactional
public class AccomodationService extends AbstractBaseService<Accomodation, Long>{
	
	private final AccomodationRepository accomodationRepository;
	
	public AccomodationService(AccomodationRepository accomodationRepository) {
		this.accomodationRepository=accomodationRepository;
		setRepository(accomodationRepository);
	}
	
	public List<Accomodation> findByDestination(Destination destination){
		return accomodationRepository.findByDestination(destination);
	}
	

	public void deleteById(Long id) {
		Accomodation accomodation=findOne(id).get();
		if(accomodation.getRooms().isEmpty()) {
			super.deleteById(id);
		}else {
			throw new ResourceConstraintException(Accomodation.class,"id", id.toString());
		}
	}

	@Override
	public Accomodation fromRequest(Long id, Accomodation request) {
		Accomodation accomodation=null;
		if(id==null) {
			accomodation=new Accomodation();
		}else {
			accomodation=findOne(id).get();
		}
	
		accomodation.setName(request.getName());
		accomodation.setProvider(request.getProvider());
		accomodation.setDescription(request.getDescription());
		accomodation.setEmail(request.getEmail());
		accomodation.setPhone(request.getPhone());
		if(request.getDestination()!=null)
			accomodation.setDestination(request.getDestination());
		return accomodation;
	}

}
