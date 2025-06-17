package com.erika.springshop.auth.application.port.in.registercustomer;

public interface RegisterUserUseCase {
    void execute(SignUpRequestDto signUpRequestDto) ;
}
