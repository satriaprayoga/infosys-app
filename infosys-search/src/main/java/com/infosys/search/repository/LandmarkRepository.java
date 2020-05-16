package com.infosys.search.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.infosys.search.domain.Landmark;

@Repository
public interface LandmarkRepository extends ElasticsearchRepository<Landmark, String>{

	Optional<Landmark> findOneById(String id);
	List<Landmark> findByDestination(String destination);
	List<Landmark> findByDestinationCode(String destinationCode);
	List<Landmark> findByLandmark(String landmark);
	
}
