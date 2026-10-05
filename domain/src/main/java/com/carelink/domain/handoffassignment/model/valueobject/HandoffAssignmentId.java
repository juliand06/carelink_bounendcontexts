package com.carelink.domain.handoffassignment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record HandoffAssignmentId(UUID value) {
    public HandoffAssignmentId {
        Objects.requireNonNull(value, "HandoffAssignmentId value must not be null");
    }

    public static HandoffAssignmentId generate() {
        return new HandoffAssignmentId(UUID.randomUUID());
    }
}
