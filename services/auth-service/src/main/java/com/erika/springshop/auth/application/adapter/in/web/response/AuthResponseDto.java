package com.erika.springshop.auth.application.adapter.in.web.response;

public record AuthResponseDto(
        Long id,
        String username,
        String email,
        String role,
        String token
) {}
