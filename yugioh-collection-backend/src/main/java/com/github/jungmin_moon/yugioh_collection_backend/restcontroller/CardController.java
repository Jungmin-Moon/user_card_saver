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
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardNameRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardQuantityRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardTypeRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
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
		
		return a.getName() + " you currently own " + cardService.returnCollectionCount(a.getName()) + " cards.";
		
	} 
	
	
	@GetMapping("/{cardName:[0-9a-zA-Z\s&.]*}")
	public String getCardInfo(Authentication a, @RequestParam String cardName) {
		
		if (cardService.isCardInDatabase(a.getName(), cardName) == false) {
			return "That card does not exist in your collection, " + a.getName();
		} else {
			Card card = cardService.getCardInfo(cardName, cardName);
			return "Card Name: " + card.getCardName() + "\n" + 
					"Card Type: " + card.getCardType() + "\n" +
					"Number Owned: " + card.getQuantity();
		}
	}
	
	@GetMapping("/{quantity:[1-9]*}")
	public String getCardsByQuantity(Authentication a, @RequestParam int quantity) {
		
		 var cardsByQuantity = cardService.getCardsByQuantity(a.getName(), quantity);
		 
		 if (cardsByQuantity.size() == 0) {
			 return "You have no cards in your collection that are exactly " + quantity + " copies.";
		 } else {
			 return cardService.cardListPrinter(cardsByQuantity);
		 }
	}
	
	@GetMapping("/word/{wordToSearch:[0-9a-zA-Z\\s&.]*}")
	public String getCardsWithWordInName(Authentication a, @RequestParam String wordToSearch) {
		
		var cardsWithWordInName = cardService.getCardsWithWordInName(a.getName(), wordToSearch);
		
		if (cardsWithWordInName.size() == 0) {
			return "No cards in your collection have the word: " + wordToSearch + " in them.";
		} else {
			return cardService.cardListPrinter(cardsWithWordInName);
		}
		
	}
	
	//should implemenet a way to check if the card is real or not but for now, anything can be added
	@PostMapping("/add")
	public ResponseEntity<?> addCard(@RequestBody NewCardRequest newCardRequest, Authentication a) {
		if (cardService.isCardInDatabase(a.getName(), newCardRequest.cardName())) {
			return new ResponseEntity<>("You have already added this card to your collection.", HttpStatus.FOUND);
		} else if (cardService.quantityGreaterThanZero(newCardRequest.quantity())){
			cardService.addCard(a.getName(), newCardRequest);
			return new ResponseEntity<>("Card added to your collection", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Card quantity must be greater than zero.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/quantity")
	public ResponseEntity<?> updateCardQuantity(@RequestBody UpdateCardQuantityRequest updateCardQuantityRequest, Authentication a) {
		//first need to check if it exists
		
		if (cardService.isCardInDatabase(a.getName(), updateCardQuantityRequest.getCardName())) {
			cardService.updateCardCount(a.getName(), updateCardQuantityRequest);
			return new ResponseEntity<>("Card name: " + updateCardQuantityRequest.getCardName() + "'s count has been updated.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Card name: " + updateCardQuantityRequest.getCardName() + " could not be found.", HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PutMapping("/update/card_name") 
	public ResponseEntity<?> updateCardName(@RequestBody UpdateCardNameRequest updateCardNameRequest, Authentication a) {
		if (cardService.isCardInDatabase(a.getName(), updateCardNameRequest.getNewCardName())) {
			cardService.updateCardName(a.getName(), updateCardNameRequest);
			return new ResponseEntity<>("Card name: " + updateCardNameRequest.getOldCardName() + " has been updated to: " + updateCardNameRequest.getNewCardName(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Card name: " + updateCardNameRequest.getOldCardName() + " could not be found.", HttpStatus.NOT_FOUND);
		}
	} 
	
	@PutMapping("/update/card_type")
	public ResponseEntity<?> updateCardType(@RequestBody UpdateCardTypeRequest updateCardTypeRequest, Authentication a) {
			if (cardService.isCardInDatabase(a.getName(), updateCardTypeRequest.getCardName())) {
				cardService.updateCardType(a.getName(), updateCardTypeRequest);
				return new ResponseEntity<>("Card name: " + updateCardTypeRequest.getCardName() + "'s card type has been updated to: " + updateCardTypeRequest.getNewCardType(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Card name: " + updateCardTypeRequest.getCardName() + " could not be found.", HttpStatus.NOT_FOUND);
			}
	}
}
