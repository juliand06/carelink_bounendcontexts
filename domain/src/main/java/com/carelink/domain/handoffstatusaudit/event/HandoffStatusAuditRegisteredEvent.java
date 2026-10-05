package com.carelink.domain.handoffstatusaudit.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public record HandoffStatusAuditRegisteredEvent(
        HandoffStatusAuditId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffStatusAuditRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
