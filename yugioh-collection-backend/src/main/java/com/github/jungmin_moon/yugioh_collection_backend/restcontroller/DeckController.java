package com.github.jungmin_moon.yugioh_collection_backend.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckRepository;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;
import com.github.jungmin_moon.yugioh_collection_backend.services.DeckService;

@RestController
@RequestMapping("/deck")
public class DeckController {

	DeckService deckService;
	DeckRepository deckRepository;
	CardService cardService;
	
	DeckController(DeckService deckService, DeckRepository deckRepository, CardService cardService) {
		
		this.deckService = deckService;
		this.deckRepository = deckRepository;
		this.cardService = cardService;
		
	}
}
