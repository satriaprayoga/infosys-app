package com.infosys.destination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.destination.domain.Accessability;
import com.infosys.destination.domain.Destination;

@Repository
public interface AccessabilityRepository extends JpaRepository<Accessability, String>{

	List<Accessability> findByDestination(Destination destination);
	
}
