package com.carelink.domain.specialist.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SpecialistId(UUID value) {
    public SpecialistId {
        Objects.requireNonNull(value, "SpecialistId value must not be null");
    }

    public static SpecialistId generate() {
        return new SpecialistId(UUID.randomUUID());
    }
}
