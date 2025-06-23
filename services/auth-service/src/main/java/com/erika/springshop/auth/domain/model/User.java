package com.erika.springshop.auth.domain.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private UUID id;

    private String username;
    private Email email;
    private Password password;

    private Role role;
    private AuthProvider authProvider;
    private String providerId;

    private boolean isEnabled;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    private boolean isCredentialsNonExpired;

    private Instant createdAt;
    private Instant updatedAt;

    public boolean isLocalUser() {
        return AuthProvider.LOCAL.equals(this.authProvider);
    }

    public boolean isActive() {
        return this.isEnabled && this.isAccountNonLocked && this.isAccountNonExpired && this.isCredentialsNonExpired;
    }

    public boolean verifyPassword(String rawPassword, PasswordEncoder encoder) {
        return password.matches(rawPassword, encoder);
    }

    public void updateTimestamps() {
        updatedAt = Instant.now();
    }

    public User createStandardUser(String username, Email email, Password password) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.role = Role.ROLE_USER;
        this.authProvider = AuthProvider.LOCAL;
        return this;
    }
}
