package com.infosys.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>,JpaSpecificationExecutor<Destination>{

	@EntityGraph(attributePaths = {"accessabilities"})
	Optional<Destination> findOneById(Long id);
	@EntityGraph(attributePaths = {"accessabilities"})
	Optional<Destination> findOneByDestinationCode(String destinationCode);
	
}
