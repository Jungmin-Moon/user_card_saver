package restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

	
	
	@GetMapping
	public void registerPage() {
		System.out.println("Please enter a username: ");
		System.out.println("Please enter your password: ");
	}
	
	@PostMapping
	public void afterRegister() {
		
	}
}
