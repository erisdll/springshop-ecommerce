package com.erika.springshop.auth.application.service;

import com.erika.springshop.auth.application.port.in.registercustomer.RegisterUserUseCase;
import com.erika.springshop.auth.domain.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    @Override
    public void execute(SignUpRequestDto signUpRequestDto) {

    }
}