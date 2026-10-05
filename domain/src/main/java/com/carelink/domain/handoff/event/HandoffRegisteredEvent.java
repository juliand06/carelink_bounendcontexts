package com.carelink.domain.handoff.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoff.model.valueobject.HandoffId;

public record HandoffRegisteredEvent(
        HandoffId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
