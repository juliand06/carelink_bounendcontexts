package com.carelink.domain.clienttrustedcontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClientTrustedContactId(UUID value) {
    public ClientTrustedContactId {
        Objects.requireNonNull(value, "ClientTrustedContactId value must not be null");
    }

    public static ClientTrustedContactId generate() {
        return new ClientTrustedContactId(UUID.randomUUID());
    }
}
