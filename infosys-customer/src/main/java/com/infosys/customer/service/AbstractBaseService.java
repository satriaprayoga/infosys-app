package com.infosys.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.customer.domain.DomainAuditable;
import com.infosys.customer.exceptions.ResourceNotFoundException;

public abstract class AbstractBaseService<S extends DomainAuditable,ID> implements BaseService<S,ID>, PageAndSortingService<S, ID>{
	
	protected JpaRepository<S, ID> repository;

	@Override
	public List<S> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<S> findOne(ID id) {
		return repository.findById(id);
	}

	@Override
	public S create(S request) {
		
		return repository.save(request);
	}
	
	public S save(S request) {
		return repository.save(request);
	}

	@Override
	public void deleteById(ID id) {
		S result=findOne(id).get();
		if(result!=null) {
			repository.delete(result);
		}else {
			throw new ResourceNotFoundException(DomainAuditable.class, "id",id.toString());
		}
	}

	
	public void setRepository(JpaRepository<S, ID> repository) {
		this.repository = repository;
	}
	
	@Override
	public Page<S> findAll(int size, int page, String sort, String direction) {
		Pageable paging=null;
		if(direction.equalsIgnoreCase("desc")) {
			paging=PageRequest.of(page, size, Sort.by(sort).descending());
		}else {
			paging=PageRequest.of(page, size, Sort.by(sort).ascending());
		}
		
		return repository.findAll(paging);
	}
	

}
