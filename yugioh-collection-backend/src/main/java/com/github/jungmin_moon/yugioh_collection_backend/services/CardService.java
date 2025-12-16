package com.github.jungmin_moon.yugioh_collection_backend.services;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
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
	
	
	public boolean checkIfAlreadyAdded(String username, String cardName) {
		boolean alreadyAdded = false;
		
		var potentialCard = cardRepository.checkIfCardAlreadyAdded(username, cardName);
		
		if (potentialCard == null) {
			alreadyAdded = true;
		}
		
		return alreadyAdded;
	}
	
	public void addCard(String username, NewCardRequest newCardRequest) {
		Card card = new Card();
		
		card.setCardName(newCardRequest.getCardName());
		card.setCardType(newCardRequest.getCardType());
		card.setQuantity(newCardRequest.getQuantity());
		card.setUsername(username);
		
		cardRepository.save(card);
	}
}
