package com.carelink.domain.specialistcredential.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SpecialistCredentialId(UUID value) {
    public SpecialistCredentialId {
        Objects.requireNonNull(value, "SpecialistCredentialId value must not be null");
    }

    public static SpecialistCredentialId generate() {
        return new SpecialistCredentialId(UUID.randomUUID());
    }
}
