package com.infosys.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Additional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long>{

}
