package com.carelink.domain.security.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.security.model.valueobject.RefreshTokenId;
import com.carelink.domain.security.model.valueobject.UserId;

public class RefreshToken {
    private final RefreshTokenId id;
    private final UserId userId;
    private final String token;
    private final LocalDateTime expiresAt;
    private boolean revoked;

    private RefreshToken(RefreshTokenId id, UserId userId, String token,
                         LocalDateTime expiresAt, boolean revoked) {
        this.id = Objects.requireNonNull(id, "RefreshTokenId must not be null");
        this.userId = Objects.requireNonNull(userId, "UserId must not be null");
        this.token = Objects.requireNonNull(token, "Token string must not be null");
        this.expiresAt = Objects.requireNonNull(expiresAt, "ExpiresAt must not be null");
        this.revoked = revoked;
    }

    public static RefreshToken create(UserId userId, String token, LocalDateTime expiresAt) {
        return new RefreshToken(RefreshTokenId.generate(), userId, token, expiresAt, false);
    }

    public static RefreshToken restore(RefreshTokenId id, UserId userId, String token,
                                       LocalDateTime expiresAt, boolean revoked) {
        return new RefreshToken(id, userId, token, expiresAt, revoked);
    }

    public void revoke() {
        this.revoked = true;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return !revoked && !isExpired();
    }

    public RefreshTokenId id() { return id; }
    public UserId userId() { return userId; }
    public String token() { return token; }
    public LocalDateTime expiresAt() { return expiresAt; }
    public boolean isRevoked() { return revoked; }
}
