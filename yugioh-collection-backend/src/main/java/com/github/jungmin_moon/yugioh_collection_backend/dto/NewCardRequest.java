package com.github.jungmin_moon.yugioh_collection_backend.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;

public record NewCardRequest(@NotEmpty(message = "Card name must not be null or empty.") String cardName, 
							@NotEmpty(message = "Card type must not be null or empty.") String cardType, 
							@Range(min = 1, message = "Quantity must be 1 or greater") int quantity) {

}
