package com.carelink.domain.careplan.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record CarePlanId(UUID value) {
    public CarePlanId {
        Objects.requireNonNull(value, "CarePlanId value must not be null");
    }

    public static CarePlanId generate() {
        return new CarePlanId(UUID.randomUUID());
    }
}
