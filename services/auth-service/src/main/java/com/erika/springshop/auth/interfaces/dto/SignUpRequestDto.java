package com.erika.springshop.auth.interfaces.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDto(@NotBlank String username,
                               @Email String email,
                               @NotBlank String password
) {}
