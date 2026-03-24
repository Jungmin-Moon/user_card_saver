package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;

@Service
public class DeckContentCounterService {

	public int getNumberOfCardsInMainDeck(List<DeckContents> deckContentsMainList) {
		
		int mainDeckCount = 0;
		
		for (DeckContents md : deckContentsMainList) {
			
			mainDeckCount += md.getQuantity();
			
		}
		
		return mainDeckCount;
	}
	
	
	public int getNumberOfCardsInSideDeck(List<DeckContents> deckContentsSideList) {
		
		int sideDeckCount = 0;
		
		for (DeckContents sd : deckContentsSideList) {
			
			sideDeckCount += sd.getQuantity();
			
		}
		
		
		return sideDeckCount;
	}
	
}
