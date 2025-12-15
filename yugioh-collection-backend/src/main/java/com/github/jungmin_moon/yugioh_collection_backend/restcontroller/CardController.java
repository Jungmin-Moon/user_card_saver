package com.github.jungmin_moon.yugioh_collection_backend.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

	
	@GetMapping
	public String testRole() {
		return "Hello, user or admin";
	}
}
