package com.carelink.domain.handoffassignment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record HandoffAssignmentUpdatedEvent(
        HandoffAssignmentId id,
        HandoffId handoffId,
        SpecialistId specialistId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HandoffAssignmentUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(handoffId, "handoffId must not be null");
        Objects.requireNonNull(specialistId, "specialistId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
