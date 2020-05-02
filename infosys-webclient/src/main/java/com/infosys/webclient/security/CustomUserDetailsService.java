package com.infosys.webclient.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosys.webclient.dto.CustomerAuthDTO;
import com.infosys.webclient.service.clients.CustomerDataService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	

	@Autowired
	private CustomerDataService customerDataService;
	
	public CustomUserDetailsService() {
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<CustomerAuthDTO> resp = customerDataService.findByEmail(username);
		if (resp.getStatusCode().equals(HttpStatus.OK)) {
			return createFromResponse(resp);
		} else {
			throw new UsernameNotFoundException("User not found for: " + username);
		}
	}

	private UserPrincipal createFromResponse(ResponseEntity<CustomerAuthDTO> resp) {
		CustomerAuthDTO dto=resp.getBody();
		UserPrincipal principal=new UserPrincipal(dto.getId(), dto.getEmail(), dto.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(new String("ROLE_USER"))));
		principal.setEnabled(dto.isEnabled());
		return principal;
	}

	public UserPrincipal loadById(String id) {
		ResponseEntity<CustomerAuthDTO> resp = customerDataService.findById(id);
		if (resp.getStatusCode().equals(HttpStatus.OK)) {
			return createFromResponse(resp);
		} else {
			throw new UsernameNotFoundException("User not found for: " + id);
		}
	}

	
	
	

}
