package com.github.jungmin_moon.yugioh_collection_backend.services;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewDeckRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckRepository;

@Service
public class DeckService {

	private DeckRepository deckRepository;
	private DeckPrinterService deckPrinterService;
	
	DeckService(DeckRepository deckRepository, DeckPrinterService deckPrinterService) {
		
		this.deckRepository = deckRepository;
		this.deckPrinterService = deckPrinterService;
		
	}
	
	public String getAll(String username) {
		
		var userDecks = deckRepository.getAllByUsername(username);
		
		if (userDecks.size() == 0) {
			return "You do not have any decks created.";
		}
		
		return deckPrinterService.printUsersDeckList(userDecks);
		
	}
}
