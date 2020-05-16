package com.infosys.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.infosys.search.domain.Group;

@Repository
public interface GroupRepository extends ElasticsearchRepository<Group, String>{

	List<Group> findByDestination(String destination);
	List<Group> findByDestinationId(String destinationId);
}
