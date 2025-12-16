package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewCardRequest {
	
	@NotNull
	private String cardName;
	
	@NotNull
	private String cardType;
	
	@NotNull
	@Size(min = 0, max = 99)
	private int quantity;

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
	
	
}
