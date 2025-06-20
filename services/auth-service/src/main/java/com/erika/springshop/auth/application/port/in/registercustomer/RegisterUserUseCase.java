package com.erika.springshop.auth.application.port.in.registercustomer;

import com.erika.springshop.auth.interfaces.dto.SignUpRequestDto;

public interface RegisterUserUseCase {
    void execute(SignUpRequestDto signUpRequestDto) ;
}
