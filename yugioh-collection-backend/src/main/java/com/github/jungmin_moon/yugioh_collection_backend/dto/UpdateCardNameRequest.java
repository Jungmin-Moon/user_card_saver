package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateCardNameRequest(@NotEmpty(message = "Old card name must not be null or empty.") String oldCardName, @NotEmpty(message = "New card name must not be null or empty.") String newCardName) {

}
