package com.erika.springshop.auth.interfaces.dto;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDto(@NotNull String username,
                             @NotNull String password) {
}