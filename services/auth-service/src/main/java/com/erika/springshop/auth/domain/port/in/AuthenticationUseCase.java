package com.erika.springshop.auth.domain.port.in;

import com.erika.springshop.auth.application.adapter.in.web.request.AuthRequestDto;
import com.erika.springshop.auth.application.adapter.in.web.response.AuthResponseDto;
import com.erika.springshop.auth.domain.model.User;

public interface AuthenticationUseCase {
    User execute(String emailStr, String rawPassword);

    AuthResponseDto execute(AuthRequestDto authRequestDto);}
