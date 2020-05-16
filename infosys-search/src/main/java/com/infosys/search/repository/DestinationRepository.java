package com.infosys.search.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.infosys.search.domain.Destination;

@Repository
public interface DestinationRepository extends ElasticsearchRepository<Destination, String>{
	
	Optional<Destination> findOneById(String id);
	Optional<Destination> findOneByCode(String code);
}
