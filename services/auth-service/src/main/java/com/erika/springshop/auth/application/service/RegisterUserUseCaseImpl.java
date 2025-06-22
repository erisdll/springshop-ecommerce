package com.erika.springshop.auth.application.service;

import com.erika.springshop.auth.application.port.in.registercustomer.RegisterUserUseCase;
import com.erika.springshop.auth.domain.model.User;
import com.erika.springshop.auth.domain.repository.UserRepository;
import com.erika.springshop.auth.interfaces.dto.SignUpRequestDto;
import com.erika.springshop.auth.interfaces.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponseDto execute(SignUpRequestDto signUpRequestDto) {

        if (userRepository.existsByEmail(signUpRequestDto.email())) {
            throw new IllegalArgumentException("Email já está em uso.");
        }

        if (userRepository.existsByUsername(signUpRequestDto.username())) {
            throw new IllegalArgumentException("Username já está em uso.");
        }

        User user = User.createStandardUser(
                signUpRequestDto.username(),
                signUpRequestDto.email(),
                passwordEncoder.encode(signUpRequestDto.password())
        );

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }
}