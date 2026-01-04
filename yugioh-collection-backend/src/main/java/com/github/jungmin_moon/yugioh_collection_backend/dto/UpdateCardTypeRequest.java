package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateCardTypeRequest(@NotEmpty(message = "Card name must not be null or empty.") String cardName, @NotEmpty(message = "New card type must not be null or empty.") String newCardType) {

}
