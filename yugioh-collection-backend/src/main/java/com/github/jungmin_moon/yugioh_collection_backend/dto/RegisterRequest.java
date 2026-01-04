package com.github.jungmin_moon.yugioh_collection_backend.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegisterRequest(@NotEmpty(message = "Username must not be null or empty.") String username, @NotEmpty(message = "Password must not be null or empty.") String password) {

}
