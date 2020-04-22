package com.infosys.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Destination;
import com.infosys.data.domain.Landmark;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long>{

	List<Landmark> findByDestination(Destination destination);

	Optional<Landmark> findOneById(Long id);
	
}
