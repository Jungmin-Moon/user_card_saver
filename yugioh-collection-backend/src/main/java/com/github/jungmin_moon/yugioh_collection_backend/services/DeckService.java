package com.github.jungmin_moon.yugioh_collection_backend.services;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewDeckRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckRepository;

@Service
public class DeckService {

	DeckRepository deckRepository;
	
	DeckService(DeckRepository deckRepository) {
		
		this.deckRepository = deckRepository;
		
	}
	
	public void createDeck(String username, NewDeckRequest newDeckRequest) {
		Decks deck = new Decks();
		deck.setUsername(username);
		deck.setDeckName(newDeckRequest.deckName());
		
		deckRepository.save(deck);
	}
	
	
	/*
	public boolean addCardToMainDeck(String username, ) {
		
	} */
	
	//Helper method to check user made sure to say card should be added to Main Deck or Side Deck, will make it so capitalization doesn't matter
	public boolean validDeckLocation(String deckLocation) {
	
		boolean correctLocationSpelling = false;
		
		if (deckLocation.equalsIgnoreCase("main deck") || deckLocation.equalsIgnoreCase("side deck")) {
			
			correctLocationSpelling = true;
			
		}
		
		return correctLocationSpelling;
	}
}
