package com.infosys.data.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.Destination;
import com.infosys.data.domain.TourGroup;
import com.infosys.data.dto.errors.ResourceConstraintException;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.repository.DestinationRepository;
import com.infosys.data.repository.TourGroupRepository;
import com.infosys.data.repository.TourPackageRepository;

@Service
@Transactional
public class TourGroupService extends AbstractBaseService<TourGroup, Long>{
	
	
	private final TourGroupRepository tourGroupRepository;
	private final DestinationRepository destinationRepository;
	private final TourPackageRepository tourPackageRepository;
	
	public TourGroupService(TourGroupRepository tourGroupRepository,DestinationRepository destinationRepository,TourPackageRepository tourPackageRepository) {
		this.tourGroupRepository=tourGroupRepository;
		this.destinationRepository=destinationRepository;
		this.tourPackageRepository=tourPackageRepository;
		setRepository(tourGroupRepository);
	}
	
	public TourGroup save(Long destId,TourGroup tourGroup) {
		return destinationRepository.findOneById(destId).map((destination)->{
			TourGroup tg=new TourGroup();
			tg.setName(tourGroup.getName());
			tg.setDestination(destination);
			return tourGroupRepository.save(tg);
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", destId.toString());
		});
	}
	
	public List<TourGroup> findByDestination(Long id){
		return destinationRepository.findById(id).map((destination)->{
			return tourGroupRepository.findByDestination(destination);
		}).orElseThrow(() -> {
			throw new ResourceNotFoundException(Destination.class, "id", id.toString());
		});
	}
	
	public TourGroup findById(Long id) {
		return tourGroupRepository.findOneById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException(TourGroup.class, "id", id.toString());
		});
	}
	
	public void deleteById(Long id) {
		Optional<TourGroup> tg=tourGroupRepository.findById(id);
		if(!tg.isPresent()) {
			throw new ResourceNotFoundException(TourGroup.class, "id", id.toString());
		}else {
			if(!tourPackageRepository.findByTourGroup(tg.get()).isEmpty()){
				throw new ResourceConstraintException(TourGroup.class, "Tour Group attached to Tour Package",id.toString());
			}else {
				tourGroupRepository.deleteById(id);
			}
		}
	}

	@Override
	protected TourGroup fromRequest(Long id, TourGroup request) {
		TourGroup tg=null;
		if(id==null) {
			tg=new TourGroup();
		}else {
			tg=findOne(id).get();
			tg.setName(request.getName());
		}
		return tg;
	}

}
