package com.erika.springshop.auth.application.port.in.registercustomer;

import com.erika.springshop.auth.interfaces.dto.SignUpRequestDto;
import com.erika.springshop.auth.interfaces.dto.SignUpResponseDto;

public interface RegisterUserUseCase {
    SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) ;
}
