package com.infosys.destination.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.destination.domain.Destination;
import com.infosys.destination.domain.TourGroup;

public interface TourGroupRepository extends JpaRepository<TourGroup, String>{
	
	Optional<TourGroup> findOneById(String id);

	List<TourGroup> findByDestination(Destination destination);
	
}
