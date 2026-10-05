package com.carelink.domain.handoffstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record HandoffStatusId(UUID value) {
    public HandoffStatusId {
        Objects.requireNonNull(value, "HandoffStatusId value must not be null");
    }

    public static HandoffStatusId generate() {
        return new HandoffStatusId(UUID.randomUUID());
    }
}
