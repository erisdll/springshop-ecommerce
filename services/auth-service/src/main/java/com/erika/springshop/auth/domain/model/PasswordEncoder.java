package com.erika.springshop.auth.domain.model;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
