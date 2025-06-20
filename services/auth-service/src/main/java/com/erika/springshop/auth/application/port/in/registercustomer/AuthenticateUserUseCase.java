package com.erika.springshop.auth.application.port.in.registercustomer;

import com.erika.springshop.auth.interfaces.dto.AuthRequestDto;

public interface AuthenticateUserUseCase {
    void execute(AuthRequestDto authRequestDto);}
