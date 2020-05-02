package com.infosys.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.customer.domain.Booking;
import com.infosys.customer.domain.BookingStatus;
import com.infosys.customer.domain.Customer;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>{

	Optional<Booking> findOneByCode(String code);
	Optional<Booking> findOneByCustomerAndCode(Customer customer,String code);
	Optional<Booking> findOneByCustomerAndCodeAndStatus(Customer customer,String code,BookingStatus bookingStatus);
	List<Booking> findByCustomer(Customer customer);
}
