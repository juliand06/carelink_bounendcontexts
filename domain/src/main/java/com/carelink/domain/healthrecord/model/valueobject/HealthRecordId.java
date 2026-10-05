package com.carelink.domain.healthrecord.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record HealthRecordId(UUID value) {
    public HealthRecordId {
        Objects.requireNonNull(value, "HealthRecordId value must not be null");
    }

    public static HealthRecordId generate() {
        return new HealthRecordId(UUID.randomUUID());
    }
}
