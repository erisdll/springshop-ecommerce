package com.erika.springshop.auth.domain.service;

import com.erika.springshop.auth.domain.model.Email;
import com.erika.springshop.auth.domain.model.User;
import com.erika.springshop.auth.domain.port.in.AuthenticationUseCase;
import com.erika.springshop.auth.application.adapter.out.security.UserPrincipal;
import com.erika.springshop.auth.domain.port.out.LoadUserPort;
import com.erika.springshop.auth.application.adapter.in.web.request.AuthRequestDto;
import com.erika.springshop.auth.application.adapter.in.web.response.AuthResponseDto;
import com.erika.springshop.auth.domain.port.out.PasswordEncoderPort;
import com.erika.springshop.auth.domain.port.out.TokenPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenPort tokenPort;

    @Override
    public User execute(String emailStr, String rawPassword) {
        Email email = new Email(emailStr);

        User user = loadUserPort.loadUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.verifyPassword(rawPassword, passwordEncoderPort)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new IllegalStateException("User account is inactive");
        }

        return user;
    }

    public String generateAccessToken(User user) {
        return tokenPort.generateAccessToken(user);
    }

    public String generateRefreshToken(User user) {
        return tokenPort.generateRefreshToken(user);
    }

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
