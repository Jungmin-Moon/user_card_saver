package services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dto.RegisterRequest;
import entities.User;
import repositories.UserRepository;

@Service
public class UserRegistrationService {

	BCryptPasswordEncoder bCryptPasswordEncoder;
	UserService userService;
	UserRepository userRepository;
	
	public void registerUser(RegisterRequest registerRequest) {
		User user = new User();
		
		boolean alreadyExists = userService.doesUserAlreadyExist(registerRequest.getUsername());
		
		if (alreadyExists == true) {
			System.out.println("You are already registered in the system.");
		} else {
		
			user.setUsername(registerRequest.getUsername());
			user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
			user.setAuthority("USER");
			
			userRepository.save(user);
		}
		
	}
}
