package com.erika.springshop.auth.interfaces.dto;

import jakarta.validation.constraints.NotNull;

public record SignUpRequestDto(@NotNull String username,
                               @NotNull String email,
                               @NotNull String password
) {}
