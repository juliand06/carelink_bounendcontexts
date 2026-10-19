package com.carelink.domain.security.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record RefreshTokenId(UUID value) {
    public RefreshTokenId {
        Objects.requireNonNull(value, "RefreshTokenId must not be null");
    }

    public static RefreshTokenId generate() {
        return new RefreshTokenId(UUID.randomUUID());
    }
}
