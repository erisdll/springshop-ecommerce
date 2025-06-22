package com.erika.springshop.auth.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.Instant;

@Getter @Setter @Builder
@Entity @Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.ROLE_USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private AuthProvider authProvider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public static User createStandardUser(String username, String email, String encodedPassword) {
        return User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role(Role.ROLE_USER)
                .enabled(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .build();
    }
}
