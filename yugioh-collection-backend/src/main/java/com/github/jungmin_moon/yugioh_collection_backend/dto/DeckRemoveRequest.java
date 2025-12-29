package com.github.jungmin_moon.yugioh_collection_backend.dto;

public record DeckRemoveRequest(String username, String cardName, String deckName, String deckLocation, int quantity) {

}
