package com.carelink.domain.caregoalstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record CareGoalStatusId(UUID value) {
    public CareGoalStatusId {
        Objects.requireNonNull(value, "CareGoalStatusId value must not be null");
    }

    public static CareGoalStatusId generate() {
        return new CareGoalStatusId(UUID.randomUUID());
    }
}
