package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;

@Service
public class DeckPrinterService {

	private ListFilterService listFilterService;
	
	public DeckPrinterService(ListFilterService listFilterService) {
		this.listFilterService = listFilterService;
	}
	
	public String printListOfUserDecks(List<Decks> decksList) {
		
		StringBuilder deckListString = new StringBuilder();
		
		deckListString.append("Deck Names List:\n");
		
		for (Decks d : decksList) {
			
			deckListString.append(d.getDeckName() + "\n");
			
		}
		
		return deckListString.toString();
		
	}
	
	public String printDeckContents(List<DeckContents> deckContentList) {
		
		StringBuilder deckContentString = new StringBuilder();
		
		var mainDeckList = listFilterService.returnListFilteredByGivenString(deckContentList, "main deck");
		var sideDeckList = listFilterService.returnListFilteredByGivenString(deckContentList, "side deck");
		
		return deckContentString.toString();
		
	}
}
