package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotEmpty;

public record NewDeckRequest(@NotEmpty(message = "Deck name must not be null or empty.") String deckName) {

}
