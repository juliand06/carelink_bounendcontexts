package com.carelink.domain.handoffstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public record HandoffStatusDeletedEvent(
        HandoffStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
