package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;

@Service
public class DeckPrinterService {

	
	public String printUsersDeckList(List<Decks> decksList) {
		
		StringBuilder deckListString = new StringBuilder();
		
		deckListString.append("Deck Names List:\n");
		
		for (Decks d : decksList) {
			
			deckListString.append(d.getDeckName() + "\n");
			
		}
		
		return deckListString.toString();
		
	}
	
}
