package com.infosys.data.service;

import org.springframework.data.domain.Page;

public interface PageAndSortingService<T,ID> {

	Page<T> findAll(int size, int page, String sort,String direction);
}
