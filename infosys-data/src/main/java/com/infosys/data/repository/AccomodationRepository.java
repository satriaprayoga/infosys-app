package com.infosys.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Accomodation;
import com.infosys.data.domain.Destination;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long>{

	List<Accomodation> findByDestination(Destination destination);
	
}
