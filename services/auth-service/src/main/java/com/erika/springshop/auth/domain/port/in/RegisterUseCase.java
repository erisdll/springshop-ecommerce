package com.erika.springshop.auth.domain.port.in;

import com.erika.springshop.auth.application.adapter.in.web.request.SignUpRequestDto;
import com.erika.springshop.auth.application.adapter.in.web.response.SignUpResponseDto;
import com.erika.springshop.auth.domain.model.User;

public interface RegisterUseCase {
    SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) ;

    User execute(String username, String emailStr, String rawPassword);
}
