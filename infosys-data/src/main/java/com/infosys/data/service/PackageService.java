package com.infosys.data.service;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.TourPackage;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.repository.TourPackageRepository;

@Service
public class PackageService extends AbstractBaseService<TourPackage, Long>{

	
	private final TourPackageRepository packageRepository;
	
	public PackageService(TourPackageRepository packageRepository) {
		this.packageRepository=packageRepository;
		setRepository(packageRepository);
	}
	
	public TourPackage findOneById(Long id) {
		return packageRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(TourPackage.class,"id",id.toString());
		});
	}
	
	@Override
	protected TourPackage fromRequest(Long id, TourPackage request) {
		TourPackage pack=null;
		if(id==null) {
			pack=new TourPackage();
		}else {
			pack=this.findOne(id).get();
			pack.setAccomodationIncluded(request.isAccomodationIncluded());
			pack.setAdditionals(request.getAdditionals());
			pack.setCapacity(request.getCapacity());
			pack.setDay(request.getDay());
			pack.setLandmarks(request.getLandmarks());
			pack.setMinOrder(request.getMinOrder());
			pack.setNight(request.getNight());
			pack.setPrice(request.getPrice());
			pack.setPublished(request.isPublished());
			pack.setTourGroup(request.getTourGroup());
			pack.setTourName(request.getTourName());
		}
		return pack;
	}

}
