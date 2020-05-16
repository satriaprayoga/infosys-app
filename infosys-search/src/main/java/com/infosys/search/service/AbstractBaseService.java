package com.infosys.search.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.infosys.search.exceptions.ResourceNotFoundException;

public abstract class AbstractBaseService<S,ID> implements BaseService<S,ID>, PageAndSortingService<S, ID>{
	
	protected ElasticsearchRepository<S, ID> repository;

	@Override
	public List<S> findAll() {
		Iterable<S> data=repository.findAll();
		if(data.iterator().hasNext()) {
			List<S> result=new ArrayList<>();
			data.forEach((d)->{
				result.add(d);
			});
			return result;
		}
		return Collections.emptyList();
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
			throw new ResourceNotFoundException("Resource with id: "+id, "not found");
		}
	}

	
	public void setRepository(ElasticsearchRepository<S, ID> repository) {
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
