package com.carelink.domain.informedconsenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record InformedConsentTypeId(UUID value) {
    public InformedConsentTypeId {
        Objects.requireNonNull(value, "InformedConsentTypeId value must not be null");
    }

    public static InformedConsentTypeId generate() {
        return new InformedConsentTypeId(UUID.randomUUID());
    }
}
