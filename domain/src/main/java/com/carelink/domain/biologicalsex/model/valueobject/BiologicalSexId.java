package com.carelink.domain.biologicalsex.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record BiologicalSexId(UUID value) {
    public BiologicalSexId {
        Objects.requireNonNull(value, "BiologicalSexId value must not be null");
    }

    public static BiologicalSexId generate() {
        return new BiologicalSexId(UUID.randomUUID());
    }
}
