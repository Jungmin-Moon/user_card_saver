package com.github.jungmin_moon.yugioh_collection_backend.services;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.CardRepository;

@Service
public class CardService {

	
	CardRepository cardRepository;
	
	CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public int returnCollectionCount(String username) {
		var collectionList = cardRepository.countCardsOwned(username);
		
		return collectionList.size();
	}
	
	public Card getCardInfo(String username, String cardName) {
		return cardRepository.getCardInfo(username, cardName);
	}
	
	public boolean isCardInDatabase(String username, String cardName) {
		boolean inDatabase = false;
		
		var potentialCard = cardRepository.getCardInfo(username, cardName);
		
		if (potentialCard == null) {
			inDatabase = true;
		}
		
		return inDatabase;
	}
	
	public void addCard(String username, NewCardRequest newCardRequest) {
		Card card = new Card();
		
		card.setCardName(newCardRequest.getCardName());
		card.setCardType(newCardRequest.getCardType());
		card.setQuantity(newCardRequest.getQuantity());
		card.setUsername(username);
		
		cardRepository.save(card);
	}
	
	public void updateCardCount(String username, UpdateCardRequest updateCardRequest) {
		Card card = cardRepository.getCardInfo(username, updateCardRequest.getCardName());
		
		if (card == null) {
			System.out.println("There is no card to update.");
		} else {
			cardRepository.updateCardQuantity(username, updateCardRequest.getUpdatedQuanity(), updateCardRequest.getCardName());
		}
	}
	
	public boolean quantityGreaterThanZero(int quantity) {
		boolean greaterThanZero = true;
		
		if (quantity <= 0) {
			greaterThanZero = false;
		}
		
		return greaterThanZero;
	}
}
