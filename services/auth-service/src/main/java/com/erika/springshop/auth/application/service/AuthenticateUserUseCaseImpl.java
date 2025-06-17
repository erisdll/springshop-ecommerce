package com.erika.springshop.auth.application.service;

import com.erika.springshop.auth.application.port.in.registercustomer.AuthenticateUserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {
    @Override
    public void execute(AuthenticationRequestDto authenticationRequestDto) {
    }
}
