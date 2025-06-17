package com.erika.springshop.auth.application.port.in.registercustomer;

public interface AuthenticateUserUseCase {
    void execute(AuthenticationRequestDto authenticationRequestDto);
}
