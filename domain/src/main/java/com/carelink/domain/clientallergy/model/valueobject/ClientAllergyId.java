package com.carelink.domain.clientallergy.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClientAllergyId(UUID value) {
    public ClientAllergyId {
        Objects.requireNonNull(value, "ClientAllergyId value must not be null");
    }

    public static ClientAllergyId generate() {
        return new ClientAllergyId(UUID.randomUUID());
    }
}
