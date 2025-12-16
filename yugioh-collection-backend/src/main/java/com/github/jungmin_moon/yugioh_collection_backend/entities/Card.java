package com.github.jungmin_moon.yugioh_collection_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Card")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@NotNull
	private String cardName;
	
	@NotNull
	private String cardType;
	
	@NotNull
	@Size(min = 0, max = 99)
	private int quantity;
	
	@NotNull
	private String username;

	/*
	public Card(long id, @NotNull String cardName, @NotNull String cardType,
			@NotNull @Size(min = 0, max = 3) int quantity, @NotNull String username) {
		super();
		this.id = id;
		this.cardName = cardName;
		this.cardType = cardType;
		this.quantity = quantity;
		this.username = username;
	} */

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
