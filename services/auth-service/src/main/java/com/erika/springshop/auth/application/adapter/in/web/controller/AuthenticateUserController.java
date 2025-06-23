package com.erika.springshop.auth.application.adapter.in.web.controller;

import com.erika.springshop.auth.domain.port.in.AuthenticationUseCase;
import com.erika.springshop.auth.application.adapter.in.web.request.AuthRequestDto;
import com.erika.springshop.auth.application.adapter.in.web.response.AuthResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/login")
@RequiredArgsConstructor
public class AuthenticateUserController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping
    public ResponseEntity<AuthResponseDto> authenticateUser(@Valid @RequestBody AuthRequestDto authRequestDto) {
        AuthResponseDto authResponseDto = authenticationUseCase.execute(authRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

}
