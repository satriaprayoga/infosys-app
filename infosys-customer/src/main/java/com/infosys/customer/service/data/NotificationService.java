package com.infosys.customer.service.data;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infosys.customer.domain.Notification;
import com.infosys.customer.exceptions.ResourceNotFoundException;
import com.infosys.customer.repository.CustomerRepository;
import com.infosys.customer.repository.NotificationRepository;
import com.infosys.customer.service.AbstractBaseService;

@Service
public class NotificationService extends AbstractBaseService<Notification, Long>{

	private final NotificationRepository notificationRepository;
	private final CustomerRepository customerRepository;
	public NotificationService(NotificationRepository notificationRepository,CustomerRepository customerRepository) {
		setRepository(notificationRepository);
		this.notificationRepository=notificationRepository;
		this.customerRepository=customerRepository;
	}
	
	public List<Notification> findByCustomer(String customerId){
		return customerRepository.findById(customerId).map((c)->{
			return notificationRepository.findByCustomer(c);
		}).orElseGet(()->{
			return Collections.emptyList();
		});
	}
	
	public Notification setAsRead(Long id) {
		return notificationRepository.findById(id).map((n)->{
			if(!n.isRead()) {
				n.setRead(true);
				return notificationRepository.save(n);
			}else {
				return n;
			}
		
		}).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(Notification.class, "id", id.toString());
		});
	}
}
