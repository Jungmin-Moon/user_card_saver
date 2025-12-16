package com.github.jungmin_moon.yugioh_collection_backend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.dto.RegisterRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.User;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.UserRepository;

@Service
public class UserRegistrationService {

	BCryptPasswordEncoder bCryptPasswordEncoder;
	//UserService userService;
	UserRepository userRepository;
	
	UserRegistrationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public void registerUser(RegisterRequest registerRequest) {
		User user = new User();
				
		user.setUsername(registerRequest.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
		user.setAuthority("USER");
			
		userRepository.save(user);
		
		
	}
}
