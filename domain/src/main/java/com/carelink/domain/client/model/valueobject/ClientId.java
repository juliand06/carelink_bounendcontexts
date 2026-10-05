package com.carelink.domain.client.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClientId(UUID value) {
    public ClientId {
        Objects.requireNonNull(value, "ClientId value must not be null");
    }

    public static ClientId generate() {
        return new ClientId(UUID.randomUUID());
    }
}
