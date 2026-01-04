package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DeckAddRequest(@NotEmpty(message = "Username must not be null or empty.") String username, 
							@NotEmpty(message = "Card name must not be null of empty.") String cardName, 
							@NotEmpty(message = "Deck name must not be null or empty.") String deckName, 
							@NotEmpty(message = "Deck location must not be null or empty.") String deckLocation, 
							@Size(min = 1, message = "Quantity must be 1 or greater.") int quantity) {

}
