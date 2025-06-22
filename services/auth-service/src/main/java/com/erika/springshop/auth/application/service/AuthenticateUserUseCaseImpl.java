package com.erika.springshop.auth.application.service;

import com.erika.springshop.auth.application.port.in.registercustomer.AuthenticateUserUseCase;
import com.erika.springshop.auth.infrastructure.security.UserPrincipal;
import com.erika.springshop.auth.infrastructure.security.jwt.JwtService;
import com.erika.springshop.auth.interfaces.dto.AuthRequestDto;
import com.erika.springshop.auth.interfaces.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto execute(AuthRequestDto authRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.username(),
                        authRequestDto.password()
                )
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String token = jwtService.generateToken(userPrincipal);

        return new AuthResponseDto(
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getAuthorities().iterator().next().getAuthority(),
                token
        );
    }
}
