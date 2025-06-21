package com.erika.springshop.auth.interfaces.dto;

public record SignUpResponseDto(
        Long userId,
        String username,
        String email,
        String role
) {}

