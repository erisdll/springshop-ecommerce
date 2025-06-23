package com.erika.springshop.auth.domain.service;

import com.erika.springshop.auth.application.adapter.in.web.request.SignUpRequestDto;
import com.erika.springshop.auth.application.adapter.in.web.response.SignUpResponseDto;
import com.erika.springshop.auth.domain.model.*;
import com.erika.springshop.auth.domain.port.in.RegisterUseCase;
import com.erika.springshop.auth.domain.port.out.LoadUserPort;
import com.erika.springshop.auth.domain.port.out.PasswordEncoderPort;
import com.erika.springshop.auth.domain.port.out.SaveUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {

    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) {
        return null;
    }

    @Override
    public User execute(String username, String emailStr, String rawPassword) {
        Email email = new Email(emailStr);

        Optional<User> existing = loadUserPort.loadUserByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        Password password = Password.encode(rawPassword, passwordEncoderPort);

        User user = User.builder()
                .id(null)
                .username(username)
                .email(email)
                .password(password)
                .role(Role.ROLE_USER)
                .authProvider(AuthProvider.LOCAL)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .createdAt(null)
                .updatedAt(null)
                .build();

        return saveUserPort.save(user);
    }
}