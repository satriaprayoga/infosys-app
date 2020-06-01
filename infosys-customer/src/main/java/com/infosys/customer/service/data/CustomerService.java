package com.infosys.customer.service.data;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.customer.domain.AuthProvider;
import com.infosys.customer.domain.Customer;
import com.infosys.customer.dto.payload.CustomerInfoRequest;
import com.infosys.customer.dto.payload.CustomerRequest;
import com.infosys.customer.exceptions.ResourceNotFoundException;
import com.infosys.customer.repository.CustomerRepository;
import com.infosys.customer.service.AbstractBaseService;
import com.infosys.customer.utils.RandomUtils;

@Service
public class CustomerService extends AbstractBaseService<Customer, String> {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		setRepository(customerRepository);
	}
	@Transactional(readOnly = true)
	public Customer findById(String id) {
		return customerRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Customer.class, "email", id);
		});
	}
	@Transactional(readOnly = true)
	public Customer findByEmail(String email) {
		return customerRepository.findOneByEmail(email).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Customer.class, "email", email);
		});
	}
	@Transactional(readOnly = true)
	public Customer findByPhone(String phone) {
		return customerRepository.findOneByPhone(phone).<ResourceNotFoundException>orElseThrow(() -> {
			throw new ResourceNotFoundException(Customer.class, "email", phone);
		});
	}
	@Transactional(readOnly = true)
	public Boolean existsByEmail(String email) {
		return customerRepository.existsByEmail(email);
	}
	
	public Boolean existsByPhone(String phone) {
		return customerRepository.existsByPhone(phone);
	}
	@Transactional
	public Customer registerNew(CustomerRequest request) {
		Customer customer=new Customer();
		customer.setAddress(request.getAddress());
		customer.setCity(request.getCity());
		customer.setCountry(request.getCountry());
		customer.setEmail(request.getEmail());
		customer.setPassword(request.getPassword());
		customer.setPhone(request.getPhone());
		customer.setProvider(AuthProvider.valueOf(request.getProvider()));
		customer.setProviderId(request.getProviderId());
		customer.setImageUrl(request.getImageUrl());
		customer.setState(request.getState());
		customer.setUsername(request.getUsername());
		customer.setZipCode(request.getZipCode());
		customer.setEnabled(false);
		customer.setActivationKey(RandomUtils.generateRandomAlphanumeric(6));
		customer.setActivationExpired(customer.calculateExpiredDate(Customer.EXPIRATION));
		return super.create(customer);
	}
	@Transactional
	public Customer updateUserInfo(String id,CustomerInfoRequest request) {
		Optional<Customer> customer=findOne(id);
		if(!customer.isPresent()) {
			throw new ResourceNotFoundException(Customer.class,"id",id);
		}
		Customer c=customer.get();
		c.setAddress(request.getAddress());
		c.setCity(request.getCity());
		c.setCountry(request.getCity());
		c.setState(request.getState());
		c.setZipCode(request.getZipCode());
		return super.save(c);
	}
	@Transactional
	public Customer activateCustomer(String id,String activationKey) {
		return customerRepository.findById(id).map(c->{
			if(c.getActivationKey().equals(activationKey)) {
					
				c.setEnabled(true);
			}
			return super.save(c);
		}).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(Customer.class, "activationKey", activationKey);
		});
	}
	
	@Transactional(readOnly = true)
	public Boolean isActivationExipred(String id) {
		Customer c=customerRepository.getOne(id);
		Calendar cal=Calendar.getInstance();
		return ((c.getActivationExpired().getTime()-cal.getTime().getTime())<=0);
	}
	@Transactional
	public Customer changePassword(String id, String password) {
		return customerRepository.findById(id).map(c->{
			c.setPassword(password);
			return super.save(c);
		}).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException(Customer.class, "id", id);
		});
	}
	
	@Transactional(readOnly = true)
	public Boolean isProfileCompleted(String id) {
		Customer c=customerRepository.getOne(id);
		return c.isProfileCompleted();
	}

}
