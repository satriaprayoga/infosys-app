package com.infosys.destination.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.destination.domain.Destination;
import com.infosys.destination.domain.TourGroup;
import com.infosys.destination.domain.TourPackage;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, String>{


	@EntityGraph(attributePaths = {"landmarks","additionals"})
	Optional<TourPackage> findOneById(String id);
	
	
	List<TourPackage> findByTourGroupDestination(Destination destination);
	@EntityGraph(attributePaths = {"landmarks","additionals"})
	List<TourPackage> findByTourGroup(TourGroup tourGroup);
	
	List<TourPackage> findByLandmarksId(String id);
	
	List<TourPackage> findByAdditionalsId(String id);
}
