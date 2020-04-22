package com.infosys.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

   Optional<Customer> findByEmail(String email);

   Optional<Customer> findByUsernameOrEmail(String username, String email);

   List<Customer> findByIdIn(List<Long> CustomerIds);

   Optional<Customer> findByUsername(String username);

   Boolean existsByUsername(String username);

   Boolean existsByEmail(String email);
}
