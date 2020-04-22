package com.infosys.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.data.domain.Booking;
import com.infosys.data.domain.Customer;
import com.infosys.data.domain.Tour;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>{

	List<Booking> findByCustomer(Customer customer);
	List<Booking> findByCustomerId(Long id);
	List<Booking> findByTour(Tour tour);
	List<Booking> findByTourId(Long id);
	List<Booking> findByCheckin(LocalDate checkin);
	
}
