package com.carelink.domain.security.model.aggregate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.security.event.UserRegisteredEvent;
import com.carelink.domain.security.exception.DomainSecurityException;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.model.valueobject.UserStatus;

public class User extends AggregateRoot {
    private final UserId id;
    private final String email;
    private String password;
    private final Set<Role> roles;
    private UserStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User(UserId id, String email, String password, Set<Role> roles,
                 UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "UserId must not be null");
        this.email = Objects.requireNonNull(email, "Email must not be null");
        this.password = Objects.requireNonNull(password, "Password must not be null");
        this.roles = new HashSet<>(roles != null ? roles : Collections.emptySet());
        this.status = Objects.requireNonNull(status, "Status must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "UpdatedAt must not be null");
    }

    public static User create(String email, String hashedPassword) {
        if (email == null || email.isBlank()) {
            throw new DomainSecurityException("Email no puede estar vacío");
        }
        if (hashedPassword == null || hashedPassword.isBlank()) {
            throw new DomainSecurityException("Password no puede estar vacío");
        }
        if (!isValidEmail(email)) {
            throw new DomainSecurityException("Email con formato inválido");
        }

        UserId userId = UserId.generate();
        LocalDateTime now = LocalDateTime.now();
        User user = new User(userId, email.toLowerCase().trim(), hashedPassword,
                new HashSet<>(), UserStatus.ACTIVE, now, now);
        user.recordEvent(new UserRegisteredEvent(userId, now));
        return user;
    }

    public static User restore(UserId id, String email, String password, Set<Role> roles,
                               UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new User(id, email, password, roles, status, createdAt, updatedAt);
    }

    public void assignRole(Role role) {
        if (role == null) {
            throw new DomainSecurityException("El rol no puede ser nulo");
        }
        this.roles.add(role);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeRole(Role role) {
        if (role == null) {
            throw new DomainSecurityException("El rol no puede ser nulo");
        }
        this.roles.remove(role);
        this.updatedAt = LocalDateTime.now();
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    public void changePassword(String newHashedPassword) {
        if (newHashedPassword == null || newHashedPassword.isBlank()) {
            throw new DomainSecurityException("La nueva contraseña no puede estar vacía");
        }
        this.password = newHashedPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public void block() {
        this.status = UserStatus.BLOCKED;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public UserId id() { return id; }
    public String email() { return email; }
    public String password() { return password; }
    public Set<Role> roles() { return Collections.unmodifiableSet(roles); }
    public UserStatus status() { return status; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
