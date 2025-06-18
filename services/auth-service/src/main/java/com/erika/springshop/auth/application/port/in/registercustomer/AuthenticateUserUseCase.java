package com.erika.springshop.auth.application.port.in.registercustomer;

import com.erika.springshop.auth.domain.dto.AuthenticationRequestDto;

public interface AuthenticateUserUseCase {
    void execute(AuthenticationRequestDto authenticationRequestDto);}
