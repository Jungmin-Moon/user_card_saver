package com.github.jungmin_moon.yugioh_collection_backend.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

	CardService cardService;
	
	CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	@GetMapping("/countAll")
	public String cardCollection(Authentication a) {
		
		return a.getName() + " you currently own " + cardService.returnCollectionCount(a.getName() + " cards.");
		
	} 
	
	
	@GetMapping("/{cardName:[0-9a-zA-Z\\s&.]*}")
	public String getCardInfo(Authentication a, @RequestParam String cardName) {
		
		
		
		return "";
	}
	
	//should implemenet a way to check if the card is real or not but for now, anything can be added
	@PostMapping("/add")
	public ResponseEntity<?> addCard(@RequestBody NewCardRequest newCardRequest, Authentication a) {
		if (cardService.checkIfAlreadyAdded(a.getName(), newCardRequest.getCardName())) {
			return new ResponseEntity<>("You have already added this card to your collection.", HttpStatus.BAD_REQUEST);
		} else {
			cardService.addCard(a.getName(), newCardRequest);
			return new ResponseEntity<>("Card added to your collection", HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCard(@RequestBody UpdateCardRequest updateCardRequest, Authentication a) {
		//first need to check if it exists
		
		if (cardService.isCardInDatabase(a.getName(), updateCardRequest.getCardName())) {
			cardService.updateCardCount(a.getName(), updateCardRequest);
			return new ResponseEntity<>("Card in your collection is updated.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("That card is not in your collection.", HttpStatus.BAD_REQUEST);
		}
		
	}
}
