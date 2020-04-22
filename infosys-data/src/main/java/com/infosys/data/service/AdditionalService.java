package com.infosys.data.service;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.Additional;
import com.infosys.data.repository.AdditionalRepository;

@Service
public class AdditionalService extends AbstractBaseService<Additional, Long>{

	private final AdditionalRepository additionalRepository;
	
	public AdditionalService(AdditionalRepository additionalRepository) {
		this.additionalRepository=additionalRepository;
		setRepository(additionalRepository);
	}
	
	@Override
	protected Additional fromRequest(Long id, Additional request) {
		Additional additional=null;
		if(id==null) {
			additional=new Additional();
		}
		additional=additionalRepository.findById(id).get();
		additional.setName(request.getName());
		return additional;
	}
}
