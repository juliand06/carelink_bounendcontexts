package com.carelink.domain.caregoal.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record CareGoalId(UUID value) {
    public CareGoalId {
        Objects.requireNonNull(value, "CareGoalId value must not be null");
    }

    public static CareGoalId generate() {
        return new CareGoalId(UUID.randomUUID());
    }
}
