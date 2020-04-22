package com.infosys.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, String>{

}
