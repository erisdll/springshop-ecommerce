package com.erika.springshop.auth.application.service;

import com.erika.springshop.auth.application.port.in.registercustomer.AuthenticateUserUseCase;
import com.erika.springshop.auth.interfaces.dto.AuthRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {
    @Override
    public void execute(AuthRequestDto authRequestDto) {
        throw new UnsupportedOperationException("Authentication logic not implemented yet.");
    }
}
