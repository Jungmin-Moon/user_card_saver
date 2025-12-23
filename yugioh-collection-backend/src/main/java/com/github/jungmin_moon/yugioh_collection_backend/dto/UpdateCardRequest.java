package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateCardRequest {

	
	@NotNull
	private String cardName;
	
	@NotNull
	private int updatedQuanity;
	
	@NotNull
	private String cardType;

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getUpdatedQuanity() {
		return updatedQuanity;
	}

	public void setUpdatedQuanity(int updatedQuanity) {
		this.updatedQuanity = updatedQuanity;
	}
	
	public String getCardType() {
		return cardType;
	}
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
