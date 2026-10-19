package com.carelink.domain.security.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record RoleId(UUID value) {
    public RoleId {
        Objects.requireNonNull(value, "RoleId must not be null");
    }

    public static RoleId generate() {
        return new RoleId(UUID.randomUUID());
    }
}
