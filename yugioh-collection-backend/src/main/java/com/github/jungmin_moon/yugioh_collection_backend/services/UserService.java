package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.entities.User;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.UserRepository;
import com.github.jungmin_moon.yugioh_collection_backend.security.SecurityUser;

@Service
public class UserService implements UserDetailsService{
	
	private UserRepository userRepository;

	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SecurityUser securityUser = null;
		
		if (userRepository.findByUsername(username) != null)
			securityUser = new SecurityUser(userRepository.findByUsername(username));
		//SecurityUser securityUser = new User();
		
		return securityUser;
	}

	
	public boolean doesUserAlreadyExist(String username) {
		boolean doesAlreadyExist = false;
		
		User checkUser = userRepository.findByUsername(username);
		
		if (checkUser == null) {
			doesAlreadyExist = false;
		} else {
			doesAlreadyExist = true;
		}
		
		return doesAlreadyExist;
	}
}
