package com.github.jungmin_moon.yugioh_collection_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Card")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String cardName;
	
	private String cardType;
	
	private int quantity;
	
	private String username;


	public long getId() {
		
		return id;
		
	}

	public void setId(long id) {
		
		this.id = id;
		
	}

	public String getCardName() {
		
		return cardName;
		
	}

	public void setCardName(String cardName) {
		
		this.cardName = cardName;
		
	}

	public String getCardType() {
		
		return cardType;
		
	}

	public void setCardType(String cardType) {
		
		this.cardType = cardType;
		
	}

	public int getQuantity() {
		
		return quantity;
		
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
		
	}

	public String getUsername() {
		
		return username;
		
	}

	public void setUsername(String username) {
		
		this.username = username;
		
	}
	
	
}
