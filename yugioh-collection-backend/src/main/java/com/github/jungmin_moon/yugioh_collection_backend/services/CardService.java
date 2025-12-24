package com.github.jungmin_moon.yugioh_collection_backend.services;

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
	public int returnCollectionCount(String username) {
		var collectionList = cardRepository.countCardsOwned(username);
		
		return collectionList.size();
	}
	
	public Card getCardInfo(String username, String cardName) {
		return cardRepository.getCardInfo(username, cardName);
	}
	
	
	//Methods that modify the database by adding, updating or deleting if there are methods for it
	public void addCard(String username, NewCardRequest newCardRequest) {
		Card card = new Card();
		
		card.setCardName(newCardRequest.getCardName());
		card.setCardType(newCardRequest.getCardType());
		card.setQuantity(newCardRequest.getQuantity());
		card.setUsername(username);
		
		cardRepository.save(card);
	}
	
	public void updateCardCount(String username, UpdateCardQuantityRequest updateCardQuantityRequest) {
		cardRepository.updateCardQuantity(username, updateCardQuantityRequest.getUpdatedQuanity(), updateCardQuantityRequest.getCardName());
	}
	
	public void updateCardName(String username, UpdateCardNameRequest updateCardNameRequest) {
		cardRepository.updateCardName(username, updateCardNameRequest.getOldCardName(), updateCardNameRequest.getNewCardName());
	}
	
	public void updateCardType(String username, UpdateCardTypeRequest updateCardTypeRequest) {
		cardRepository.updateCardType(username, updateCardTypeRequest.getCardName(), updateCardTypeRequest.getNewCardType());
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
}
