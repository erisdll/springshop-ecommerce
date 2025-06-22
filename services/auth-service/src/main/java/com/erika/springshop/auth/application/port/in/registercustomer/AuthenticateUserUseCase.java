package com.erika.springshop.auth.application.port.in.registercustomer;

import com.erika.springshop.auth.interfaces.dto.AuthRequestDto;
import com.erika.springshop.auth.interfaces.dto.AuthResponseDto;

public interface AuthenticateUserUseCase {
    AuthResponseDto execute(AuthRequestDto authRequestDto);}
