package com.infosys.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Accessability;
import com.infosys.data.domain.Destination;

@Repository
public interface AccessabilityRepository extends JpaRepository<Accessability, Long>{

	List<Accessability> findByDestination(Destination destination);
	
}
