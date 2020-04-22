package com.infosys.oauth.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosys.oauth.repository.UserRepository;
import com.infosys.oauth.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsernameOrEmail(username,username).map((user)->{
			return UserPrincipal.create(user);
		}).orElseThrow(()->new UsernameNotFoundException("Username or Email not found"));
		
	}
	@Transactional
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException{
		return userRepository.findById(id).map((user)->{
			return UserPrincipal.create(user);
		}).orElseThrow(()->new UsernameNotFoundException("Username or Email not found"));
	}
	

	
	

}
