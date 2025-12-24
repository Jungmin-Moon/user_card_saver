package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateCardTypeRequest {

	@NotNull
	private String cardName;
	
	@NotNull
	private String newCardType;

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getNewCardType() {
		return newCardType;
	}

	public void setNewCardType(String newCardType) {
		this.newCardType = newCardType;
	}
	
	
}
