package com.carelink.domain.handoff.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record HandoffId(UUID value) {
    public HandoffId {
        Objects.requireNonNull(value, "HandoffId value must not be null");
    }

    public static HandoffId generate() {
        return new HandoffId(UUID.randomUUID());
    }
}
