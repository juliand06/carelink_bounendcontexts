package com.carelink.domain.handoffassignment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;

public record HandoffAssignmentDeletedEvent(
        HandoffAssignmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffAssignmentDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
