package com.erika.springshop.auth.application.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(@NotBlank String username,
                             @NotBlank String password) {
}