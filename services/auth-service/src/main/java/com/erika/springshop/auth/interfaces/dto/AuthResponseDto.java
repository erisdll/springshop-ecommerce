package com.erika.springshop.auth.interfaces.dto;

public record AuthResponseDto(
        Long id,
        String username,
        String email,
        String role,
        String token
) {}
