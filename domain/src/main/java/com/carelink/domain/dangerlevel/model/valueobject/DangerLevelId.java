package com.carelink.domain.dangerlevel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DangerLevelId(UUID value) {
    public DangerLevelId {
        Objects.requireNonNull(value, "DangerLevelId value must not be null");
    }

    public static DangerLevelId generate() {
        return new DangerLevelId(UUID.randomUUID());
    }
}
