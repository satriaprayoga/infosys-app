package com.infosys.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.destination.domain.Additional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, String>{

}
