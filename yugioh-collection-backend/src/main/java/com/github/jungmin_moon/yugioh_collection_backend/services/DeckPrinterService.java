package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;

@Service
public class DeckPrinterService {

	private DeckContentListFilterService listFilterService;
	private DeckContentCounterService deckContentCounterService;
	
	public DeckPrinterService(DeckContentListFilterService listFilterService, DeckContentCounterService deckContentCounterService) {
		
		this.listFilterService = listFilterService;
		this.deckContentCounterService = deckContentCounterService;
		
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
		
		int mainDeckCount = deckContentCounterService.getNumberOfCardsInMainDeck(mainDeckList);
		int sideDeckCount = deckContentCounterService.getNumberOfCardsInSideDeck(sideDeckList);
		
		deckContentString.append("Main Deck - " + mainDeckCount + " cards\n");
		
		for (DeckContents dc : mainDeckList) {
			
			deckContentString.append(dc.getCardName() + "-" + dc.getQuantity() + "\n");
			
		}
		
		deckContentString.append("Side Deck - " + sideDeckCount + " cards\n");
		
		for (DeckContents dc : sideDeckList) {
			
			deckContentString.append(dc.getCardName() + "-" + dc.getQuantity() + "\n");
			
		}
		
		return deckContentString.toString();
		
	}
}
