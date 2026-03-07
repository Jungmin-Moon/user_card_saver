package com.github.jungmin_moon.yugioh_collection_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;

@Service
public class DeckContentListFilterService {

	
	public List<DeckContents> returnListFilteredByGivenString(List<DeckContents> givenList, String filterString) {
		
		return givenList.stream().filter(deckContent -> deckContent.getCardLocation().equalsIgnoreCase(filterString)).toList();
		
	}
	
}
