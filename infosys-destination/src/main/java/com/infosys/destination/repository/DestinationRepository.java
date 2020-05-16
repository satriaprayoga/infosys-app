package com.infosys.destination.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.infosys.destination.domain.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String>,JpaSpecificationExecutor<Destination>{

	@EntityGraph(attributePaths = {"accessabilities"})
	Optional<Destination> findOneById(String id);
	@EntityGraph(attributePaths = {"accessabilities"})
	Optional<Destination> findOneByDestinationCode(String destinationCode);
	
}
