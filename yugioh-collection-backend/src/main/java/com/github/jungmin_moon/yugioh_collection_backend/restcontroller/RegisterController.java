package com.github.jungmin_moon.yugioh_collection_backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jungmin_moon.yugioh_collection_backend.dto.RegisterRequest;
import com.github.jungmin_moon.yugioh_collection_backend.services.UserRegistrationService;
import com.github.jungmin_moon.yugioh_collection_backend.services.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	UserService userService;
	
	UserRegistrationService userRegistrationService;
	
	
	RegisterController(UserService userService, UserRegistrationService userRegistrationService) {
		this.userService = userService;
		this.userRegistrationService = userRegistrationService;
	} 
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		
		if (userService.doesUserAlreadyExist(registerRequest.getUsername())) {
			return new ResponseEntity<>("Username already taken.", HttpStatus.BAD_REQUEST);
		}
		
		userRegistrationService.registerUser(registerRequest);
		
		return new ResponseEntity<>("You have successfully registered.", HttpStatus.OK);
	}
	
}
