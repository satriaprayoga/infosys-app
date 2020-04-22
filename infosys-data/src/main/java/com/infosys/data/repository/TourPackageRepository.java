package com.infosys.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Destination;
import com.infosys.data.domain.TourPackage;
import com.infosys.data.domain.TourGroup;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long>{


	@EntityGraph(attributePaths = {"landmarks","additionals"})
	Optional<TourPackage> findOneById(Long id);
	
	
	List<TourPackage> findByTourGroupDestination(Destination destination);
	@EntityGraph(attributePaths = {"landmarks","additionals"})
	List<TourPackage> findByTourGroup(TourGroup tourGroup);
	
	List<TourPackage> findByLandmarksId(Long id);
	
	List<TourPackage> findByAdditionalsId(Long id);
}
