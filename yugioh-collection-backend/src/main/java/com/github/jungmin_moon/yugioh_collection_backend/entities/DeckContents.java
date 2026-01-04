package com.github.jungmin_moon.yugioh_collection_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deck_contents")
public class DeckContents {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;
	
	private String cardName;
	
	private String deckName;
	
	private String cardLocation;
	
	private int quantity;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

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
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
