package com.carelink.domain.urgencylevel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record UrgencyLevelId(UUID value) {
    public UrgencyLevelId {
        Objects.requireNonNull(value, "UrgencyLevelId value must not be null");
    }

    public static UrgencyLevelId generate() {
        return new UrgencyLevelId(UUID.randomUUID());
    }
}
