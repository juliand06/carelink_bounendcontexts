package com.carelink.domain.handoffstatusaudit.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record HandoffStatusAuditId(UUID value) {
    public HandoffStatusAuditId {
        Objects.requireNonNull(value, "HandoffStatusAuditId value must not be null");
    }

    public static HandoffStatusAuditId generate() {
        return new HandoffStatusAuditId(UUID.randomUUID());
    }
}
