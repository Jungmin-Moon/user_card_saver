package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardNameRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardQuantityRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardTypeRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.CardRepository;

@Service
public class CardService {

	
	CardRepository cardRepository;
	
	CardService(CardRepository cardRepository) {
		
		this.cardRepository = cardRepository;
		
	}
	
	//Methods that fetch information either as a single card or multiple cards 
	
	public List<Card> getAll(String username) {
		return cardRepository.getAllOwnedByUsername(username);
	}
	
	
	public int returnCollectionCount(String username) {
		
		var collectionList = cardRepository.getAllOwnedByUsername(username);
		
		return collectionList.size();
	}
	
	public Card getCardInfo(String username, String cardName) {
		
		return cardRepository.getCardInfo(username, cardName);
		
	}
	
	public List<Card> getCardsByQuantity(String username, int quantity) {
		
		return cardRepository.getCardsByQuantity(username, quantity);
		
	}
	
	public List<Card> getCardsWithWordInName(String username, String wordToFind) {
		
		return cardRepository.getCardsWithWordInName(username, wordToFind);
		
	}
	
	//Methods that modify the database by adding, updating or deleting if there are methods for it
	public void addCard(String username, NewCardRequest newCardRequest) {
		
		Card card = new Card();
		
		card.setCardName(newCardRequest.cardName());
		card.setCardType(newCardRequest.cardType());
		card.setQuantity(newCardRequest.quantity());
		card.setUsername(username);
		
		cardRepository.save(card);
	}
	
	public void updateCardCount(String username, UpdateCardQuantityRequest updateCardQuantityRequest) {
		
		cardRepository.updateCardQuantity(username, updateCardQuantityRequest.updatedQuantity(), updateCardQuantityRequest.cardName());
		
	}
	
	public void updateCardName(String username, UpdateCardNameRequest updateCardNameRequest) {
		
		cardRepository.updateCardName(username, updateCardNameRequest.oldCardName(), updateCardNameRequest.newCardName());
		
	}
	
	public void updateCardType(String username, UpdateCardTypeRequest updateCardTypeRequest) {
		
		cardRepository.updateCardType(username, updateCardTypeRequest.cardName(), updateCardTypeRequest.newCardType());
		
	}
	
	
	//Methods to perform checks on the database or on requests
	public boolean quantityGreaterThanZero(int quantity) {
		
		boolean greaterThanZero = true;
		
		if (quantity <= 0) {
			
			greaterThanZero = false;
			
		}
		
		return greaterThanZero;
	}
	
	public boolean isCardInDatabase(String username, String cardName) {
		
		boolean inDatabase = false;
		
		var potentialCard = cardRepository.getCardInfo(username, cardName);
		
		if (potentialCard != null) {
			
			inDatabase = true;
			
		}
		
		return inDatabase;
	}
	
	public String cardListPrinter(List<Card> cardList) {
		
		StringBuilder str = new StringBuilder();
		
		for (Card c: cardList) {
			
			str.append("Card Name: " + c.getCardName() + "\n");
			str.append("Card Type: " + c.getCardType() + "\n");
			str.append("Number Owned: " + c.getQuantity() + "\n" + "\n");
			
		}
		
		return str.toString();
	}
}
