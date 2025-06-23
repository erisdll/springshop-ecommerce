package com.erika.springshop.auth.domain.model;

public record Password(String hashed) {

    public Password {
        if (hashed == null || hashed.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }

    public static Password encode(String rowPassword,  PasswordEncoder encoder) {
        return new Password(encoder.encode(rowPassword));
    }

    public boolean matches(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.hashed);
    }
}
