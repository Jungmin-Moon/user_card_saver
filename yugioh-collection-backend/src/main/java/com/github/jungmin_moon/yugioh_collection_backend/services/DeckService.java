package com.github.jungmin_moon.yugioh_collection_backend.services;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckContentsRepository;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckRepository;

@Service
public class DeckService {

	private DeckRepository deckRepository;
	private DeckContentsRepository deckContentsRepository;
	private DeckPrinterService deckPrinterService;
	
	DeckService(DeckRepository deckRepository, DeckPrinterService deckPrinterService, DeckContentsRepository deckContentsRepository) {
		
		this.deckRepository = deckRepository;
		this.deckPrinterService = deckPrinterService;
		this.deckContentsRepository = deckContentsRepository;
		
	}
	
	public String getAll(String username) {
		
		var userDecks = deckRepository.getAllByUsername(username);
		
		if (userDecks.size() == 0) {
			return "You do not have any decks created.";
		}
		
		return deckPrinterService.printListOfUserDecks(userDecks);
		
	}
	
	public String getDeckContent(String username, String deckName) {
		
		var deckContents = deckContentsRepository.getAllByDeckName(deckName, username);
		
		if (deckContents.size() == 0) {
			return deckName + ": This deck list is empty currently.";
		}
		
		return deckPrinterService.printDeckContents(deckContents);
		
	}
	
	/*
	 * TODO
	 * 
	 * GET see a list of cards in deck
	should show how many in main deck and how many in side deck
	
		card name xNumber
		Main Deck - #
		card name - #

		Side Deck - #/15
		card name - #

		POST add a card to deck
			needs to check if user owns that card
			needs a check to see if there are already three copies in deck in a service
			
		POST create a new deck with name

	 */
}
