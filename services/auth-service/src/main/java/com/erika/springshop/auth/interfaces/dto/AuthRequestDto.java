package com.erika.springshop.auth.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(@NotBlank String username,
                             @NotBlank String password) {
}