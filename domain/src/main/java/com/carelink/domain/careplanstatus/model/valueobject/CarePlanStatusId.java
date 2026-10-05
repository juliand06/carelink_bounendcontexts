package com.carelink.domain.careplanstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record CarePlanStatusId(UUID value) {
    public CarePlanStatusId {
        Objects.requireNonNull(value, "CarePlanStatusId value must not be null");
    }

    public static CarePlanStatusId generate() {
        return new CarePlanStatusId(UUID.randomUUID());
    }
}
