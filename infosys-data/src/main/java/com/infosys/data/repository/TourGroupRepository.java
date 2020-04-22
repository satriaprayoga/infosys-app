package com.infosys.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.data.domain.Destination;
import com.infosys.data.domain.TourGroup;

public interface TourGroupRepository extends JpaRepository<TourGroup, Long>{
	
	Optional<TourGroup> findOneById(Long id);

	List<TourGroup> findByDestination(Destination destination);
	
}
