package com.erika.springshop.auth.domain.model;

import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_REGEX_ROBUST = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    public Email {
        if (value == null || !EMAIL_REGEX_ROBUST.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
    }

    @Override
    public String toString() {
        return value.toLowerCase();
    }
}
