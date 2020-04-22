package com.infosys.data.service;

import org.springframework.stereotype.Service;

import com.infosys.data.domain.Customer;
import com.infosys.data.dto.errors.ResourceNotFoundException;
import com.infosys.data.repository.CustomerRepository;

@Service
public class CustomerService extends AbstractBaseService<Customer, String>{

	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
		setRepository(customerRepository);
	}
	
	
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email).orElseThrow(()->{
			throw new ResourceNotFoundException(Customer.class, "email", email);
		});
	}
	
	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username).orElseThrow(()->{
			throw new ResourceNotFoundException(Customer.class, "username", username);
		});
	}
	
	public Customer findByUsernameOrEmail(String username,String email) {
		return customerRepository.findByUsernameOrEmail(username, email).orElseThrow(()->{
			throw new ResourceNotFoundException(Customer.class, "username","email", username,email);
		});
	}
	
	public Boolean isUsernameExist(String username) {
		return customerRepository.existsByUsername(username);
	}
	
	public Boolean isEmailExist(String email) {
		return customerRepository.existsByEmail(email);
	}
	
	@Override
	protected Customer fromRequest(String id, Customer request) {
		Customer customer=null;
		if(id==null) {
			 customer=new Customer();
		}else {
			customer=findOne(id).get();
		}
		customer.setUsername(request.getUsername());
		customer.setPassword(request.getPassword());
		customer.setEmail(request.getPassword());
		customer.setProvider(request.getProvider());
		customer.setPhone(request.getPhone());
		customer.setAddress(request.getAddress());
		customer.setAccountNonExpired(request.isAccountNonExpired());
		customer.setAccountNonLocked(request.isAccountNonLocked());
		customer.setCredentialsNonExpired(request.isCredentialsNonExpired());
		customer.setEnabled(request.isEnabled());
		return customer;
	}

}
