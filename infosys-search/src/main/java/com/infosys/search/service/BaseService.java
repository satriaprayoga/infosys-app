package com.infosys.search.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,ID> {

	List<T> findAll();
	Optional<T> findOne(ID id);
	T create(T request);
	void deleteById(ID id);
	
}
