package com.erika.springshop.auth.interfaces.controller;

import com.erika.springshop.auth.application.port.in.registercustomer.AuthenticateUserUseCase;
import com.erika.springshop.auth.interfaces.dto.AuthRequestDto;
import com.erika.springshop.auth.interfaces.dto.AuthResponseDto;
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

    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping
    public ResponseEntity<AuthResponseDto> authenticateUser(@Valid @RequestBody AuthRequestDto authRequestDto) {
        AuthResponseDto authResponseDto = authenticateUserUseCase.execute(authRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

}
