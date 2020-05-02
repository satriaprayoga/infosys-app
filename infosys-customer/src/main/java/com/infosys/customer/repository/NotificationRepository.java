package com.infosys.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.customer.domain.Customer;
import com.infosys.customer.domain.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

	List<Notification> findByCustomer(Customer customer);
	List<Notification> findByRead(Boolean read);
	
	
}
