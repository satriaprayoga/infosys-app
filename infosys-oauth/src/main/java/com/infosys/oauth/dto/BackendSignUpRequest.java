package com.infosys.oauth.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.infosys.oauth.domain.Role;
import com.infosys.oauth.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackendSignUpRequest {

	private String username;
	private String email;
	private String password;
	private List<String> authorities=new ArrayList<>();
	
	public static User toUser(BackendSignUpRequest request) {
		User user=new User();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setEmail(request.getEmail());
		user.setAccountNonExpired(true);
		user.setUsername(request.getUsername());
		user.setAccountNonLocked(true);
		user.setEnabled(true);
		if(!request.getAuthorities().isEmpty()) {
			List<Role> roles=request.getAuthorities().stream().map(auth->{
				Role role=new Role();
				role.setAuthority(auth);
				return role;
			}).collect(Collectors.toList());
			for(Role r:roles) {user.getRoles().add(r);}
		}
		return user;
	}
}
