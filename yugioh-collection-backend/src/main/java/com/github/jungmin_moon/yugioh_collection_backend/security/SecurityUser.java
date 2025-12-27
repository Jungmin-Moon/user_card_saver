package com.github.jungmin_moon.yugioh_collection_backend.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.jungmin_moon.yugioh_collection_backend.entities.User;

public class SecurityUser implements UserDetails{

	
	private final User user;
	
	public SecurityUser(User user) {
		
		this.user = user;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getAuthority());
		
		//return List.of(new SimpleGrantedAuthority("ROLE_" + user.getAuthority()));
		
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
		
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
		
	}
	
	
}
