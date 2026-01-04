package com.github.jungmin_moon.yugioh_collection_backend.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateCardQuantityRequest(@NotEmpty(message = "Card name must not be null or empty.") String cardName, @Range(min = 1) int updatedQuantity) {

}
