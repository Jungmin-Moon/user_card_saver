package com.github.jungmin_moon.yugioh_collection_backend.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

	CardService cardService;
	
	CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	@GetMapping
	public String testRole(Authentication a) {
		return "Hello, " + a.getName();
	}
	
	
	@GetMapping("/countAll")
	public String cardCollection(Authentication a) {
		
		return a.getName() + " you currently own " + cardService.returnCollectionCount(a.getName() + " cards.");
		
	} 
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addCard(@RequestBody NewCardRequest newCardRequest, Authentication a) {
		if (cardService.checkIfAlreadyAdded(a.getName(), newCardRequest.getCardName())) {
			return new ResponseEntity<>("You have already added this card to your collection.", HttpStatus.BAD_REQUEST);
		} else {
			cardService.addCard(a.getName(), newCardRequest);
			return new ResponseEntity<>("Card added to your collection", HttpStatus.CREATED);
		}
	}
}
