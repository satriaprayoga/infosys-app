package com.infosys.search.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.infosys.search.domain.Tour;

@Repository
public interface TourRepository extends ElasticsearchRepository<Tour, String>{

	Optional<Tour> findOneById(String id);
}
