package com.carelink.domain.trustedcontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record TrustedContactId(UUID value) {
    public TrustedContactId {
        Objects.requireNonNull(value, "TrustedContactId value must not be null");
    }

    public static TrustedContactId generate() {
        return new TrustedContactId(UUID.randomUUID());
    }
}
