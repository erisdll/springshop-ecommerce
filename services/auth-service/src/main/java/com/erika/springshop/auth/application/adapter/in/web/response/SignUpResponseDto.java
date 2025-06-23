package com.erika.springshop.auth.application.adapter.in.web.response;

public record SignUpResponseDto(
        Long userId,
        String username,
        String email
) {}

