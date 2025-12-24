package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateCardQuantityRequest {

	
	@NotNull
	private String cardName;
	
	@NotNull
	private int updatedQuanity;

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

}
