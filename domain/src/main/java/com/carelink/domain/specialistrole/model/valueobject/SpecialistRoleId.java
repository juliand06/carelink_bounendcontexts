package com.carelink.domain.specialistrole.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SpecialistRoleId(UUID value) {
    public SpecialistRoleId {
        Objects.requireNonNull(value, "SpecialistRoleId value must not be null");
    }

    public static SpecialistRoleId generate() {
        return new SpecialistRoleId(UUID.randomUUID());
    }
}
