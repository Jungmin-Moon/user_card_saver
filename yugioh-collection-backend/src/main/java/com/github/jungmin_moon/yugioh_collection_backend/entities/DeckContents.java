package com.github.jungmin_moon.yugioh_collection_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "deck_contents")
public class DeckContents {

	@NotNull
	private String username;
	
	@NotNull
	private String cardName;
	
	@NotNull
	private String deckName;
	
	@NotNull
	private String cardLocation;

	public String getUsername() {
		
		return username;
		
	}

	public void setUsername(String username) {
		
		this.username = username;
		
	}

	public String getCardName() {
		
		return cardName;
		
	}

	public void setCardName(String cardName) {
		
		this.cardName = cardName;
		
	}

	public String getDeckName() {
		
		return deckName;
		
	}

	public void setDeckName(String deckName) {
		
		this.deckName = deckName;
		
	}

	public String getCardLocation() {
		
		return cardLocation;
		
	}

	public void setCardLocation(String cardLocation) {
		
		this.cardLocation = cardLocation;
		
	}
	
	
}
