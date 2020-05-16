package com.infosys.destination.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.destination.domain.Destination;
import com.infosys.destination.domain.Landmark;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, String>{

	List<Landmark> findByDestination(Destination destination);

	Optional<Landmark> findOneById(String id);
	
}
