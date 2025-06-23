package com.erika.springshop.auth.application.adapter.in.web.controller;

import com.erika.springshop.auth.domain.port.in.RegisterUseCase;
import com.erika.springshop.auth.application.adapter.in.web.request.SignUpRequestDto;
import com.erika.springshop.auth.application.adapter.in.web.response.SignUpResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/signup")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUseCase registerUseCase;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = registerUseCase.execute(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponseDto);
    }
}
