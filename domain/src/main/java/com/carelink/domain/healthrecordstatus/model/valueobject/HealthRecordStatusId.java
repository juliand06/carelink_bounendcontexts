package com.carelink.domain.healthrecordstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record HealthRecordStatusId(UUID value) {
    public HealthRecordStatusId {
        Objects.requireNonNull(value, "HealthRecordStatusId value must not be null");
    }

    public static HealthRecordStatusId generate() {
        return new HealthRecordStatusId(UUID.randomUUID());
    }
}
