package services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import entities.User;
import repositories.UserRepository;

public class UserService implements UserDetailsService{
	
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean doesUserAlreadyExist(String username) {
		boolean doesAlreadyExist = false;
		
		Optional<User> checkUser = userRepository.findByUsername(username);
		
		if (checkUser == null) {
			doesAlreadyExist = false;
		} else {
			doesAlreadyExist = true;
		}
		
		return doesAlreadyExist;
	}
}
