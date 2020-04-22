package com.infosys.oauth.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.infosys.oauth.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails{
	
	private static final long serialVersionUID = 4299011678188511369L;
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private boolean accountNonExpired=true;
	private boolean accountNonLocked=true;
	private boolean credentialsNonExpired=true;
	private boolean enabled=true;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getAuthority())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),true,true,true,true,authorities
        );
    }



	

}
