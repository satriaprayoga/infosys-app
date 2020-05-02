package com.infosys.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.customer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	
	   Optional<Customer> findOneById(String id);

	   Optional<Customer> findOneByEmail(String email);
	   
	   Optional<Customer> findOneByPhone(String phone);
	   
	   Optional<Customer> findOneByActivationKey(String activationKey);

	   Optional<Customer> findByUsernameOrEmail(String username, String email);

	   List<Customer> findByIdIn(List<Long> CustomerIds);

	   Optional<Customer> findByUsername(String username);

	   Boolean existsByUsername(String username);

	   Boolean existsByEmail(String email);
	   
	   Boolean existsByPhone(String phone);
}
