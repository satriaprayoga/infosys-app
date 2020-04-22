package com.infosys.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long>{

}
