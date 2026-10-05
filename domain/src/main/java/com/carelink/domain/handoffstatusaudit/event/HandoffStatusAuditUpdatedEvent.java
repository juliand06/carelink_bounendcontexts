package com.carelink.domain.handoffstatusaudit.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public record HandoffStatusAuditUpdatedEvent(
        HandoffStatusAuditId id,
        HandoffId handoffId,
        HandoffStatusId handoffStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffStatusAuditUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(handoffId, "handoffId must not be null");
        Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
