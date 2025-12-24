package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateCardNameRequest {

	
	@NotNull
	private String oldCardName;
	
	@NotNull
	private String newCardName;

	public String getOldCardName() {
		return oldCardName;
	}

	public void setOldCardName(String oldCardName) {
		this.oldCardName = oldCardName;
	}

	public String getNewCardName() {
		return newCardName;
	}

	public void setNewCardName(String newCardName) {
		this.newCardName = newCardName;
	}
	
	
}
