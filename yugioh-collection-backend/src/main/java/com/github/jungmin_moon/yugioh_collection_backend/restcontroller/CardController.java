package com.github.jungmin_moon.yugioh_collection_backend.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardNameRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardQuantityRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardTypeRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {

	CardService cardService;
	
	CardController(CardService cardService) {
		
		this.cardService = cardService;
	}
	
	@GetMapping()
	public String getAll(Authentication a) {
		
		return cardService.cardListPrinter(cardService.getAll(a.getName()));
		
	}
	
	@GetMapping("/countAll")
	public String cardCollection(Authentication a) {
		
		return a.getName() + " you currently own " + cardService.returnCollectionCount(a.getName()) + " cards.";
		
	} 
	
	@GetMapping("/{cardName:[0-9a-zA-Z &.]*}")
	public String getCardInfo(Authentication a, @PathVariable String cardName) {
		
		if (cardService.isCardInDatabase(a.getName(), cardName) == false) {
			
			return "That card does not exist in your collection, " + a.getName();
			
		} else {
			
			Card card = cardService.getCardInfo(a.getName(), cardName);
			
			return "Card Name: " + card.getCardName() + "\n" + 
					"Card Type: " + card.getCardType() + "\n" +
					"Number Owned: " + card.getQuantity();
		}
	}
	
	@GetMapping("/quantity/{quantity:[1-9]*}")
	public String getCardsByQuantity(Authentication a, @PathVariable int quantity) {
		
		 var cardsByQuantity = cardService.getCardsByQuantity(a.getName(), quantity);
		 
		 if (cardsByQuantity.size() == 0) {
			 
			 return "You have no cards in your collection that are exactly " + quantity + " copies.";
			 
		 } else {
			 
			 return cardService.cardListPrinter(cardsByQuantity);
			 
		 }
	}
	
	@GetMapping("/word/{wordToSearch:[0-9a-zA-Z\\s&.]*}")
	public String getCardsWithWordInName(Authentication a, @PathVariable String wordToSearch) {
		
		var cardsWithWordInName = cardService.getCardsWithWordInName(a.getName(), wordToSearch);
		
		if (cardsWithWordInName.size() == 0) {
			
			return "No cards in your collection have the word: " + wordToSearch + " in them.";
			
		} else {
			
			return cardService.cardListPrinter(cardsWithWordInName);
			
		}
		
	}
	
	//should implemenet a way to check if the card is real or not but for now, anything can be added
	@PostMapping("/add")
	public ResponseEntity<?> addCard(@Valid @RequestBody NewCardRequest newCardRequest, Authentication a) {
		
		if (cardService.isCardInDatabase(a.getName(), newCardRequest.cardName())) {
			
			return new ResponseEntity<>("You have already added this card to your collection.", HttpStatus.FOUND);
			
		}
			
		cardService.addCard(a.getName(), newCardRequest);
			
		return new ResponseEntity<>("Card added to your collection", HttpStatus.CREATED);
			
	}
	
	@PutMapping("/update/quantity")
	public ResponseEntity<?> updateCardQuantity(@RequestBody UpdateCardQuantityRequest updateCardQuantityRequest, Authentication a) {
		
		if (cardService.isCardInDatabase(a.getName(), updateCardQuantityRequest.cardName())) {
			
			cardService.updateCardCount(a.getName(), updateCardQuantityRequest);
			
			return new ResponseEntity<>("Card name: " + updateCardQuantityRequest.cardName() + "'s count has been updated.", HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>("Card name: " + updateCardQuantityRequest.cardName() + " could not be found.", HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	
	@PutMapping("/update/card_name") 
	public ResponseEntity<?> updateCardName(@RequestBody UpdateCardNameRequest updateCardNameRequest, Authentication a) {
		
		if (cardService.isCardInDatabase(a.getName(), updateCardNameRequest.oldCardName())) {
			
			cardService.updateCardName(a.getName(), updateCardNameRequest);
			
			return new ResponseEntity<>("Card name: " + updateCardNameRequest.oldCardName() + " has been updated to: " + updateCardNameRequest.newCardName(), HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>("Card name: " + updateCardNameRequest.oldCardName() + " could not be found.", HttpStatus.NOT_FOUND);
			
		}
	} 
	
	@PutMapping("/update/card_type")
	public ResponseEntity<?> updateCardType(@RequestBody UpdateCardTypeRequest updateCardTypeRequest, Authentication a) {
		
		if (cardService.isCardInDatabase(a.getName(), updateCardTypeRequest.cardName())) {
				
			cardService.updateCardType(a.getName(), updateCardTypeRequest);
				
			return new ResponseEntity<>("Card name: " + updateCardTypeRequest.cardName() + "'s card type has been updated to: " + updateCardTypeRequest.newCardType(), HttpStatus.OK);
				
		} else {
				
			return new ResponseEntity<>("Card name: " + updateCardTypeRequest.cardName() + " could not be found.", HttpStatus.NOT_FOUND);
		}
	}
}
