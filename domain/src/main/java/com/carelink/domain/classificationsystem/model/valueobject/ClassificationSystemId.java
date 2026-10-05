package com.carelink.domain.classificationsystem.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClassificationSystemId(UUID value) {
    public ClassificationSystemId {
        Objects.requireNonNull(value, "ClassificationSystemId value must not be null");
    }

    public static ClassificationSystemId generate() {
        return new ClassificationSystemId(UUID.randomUUID());
    }
}
