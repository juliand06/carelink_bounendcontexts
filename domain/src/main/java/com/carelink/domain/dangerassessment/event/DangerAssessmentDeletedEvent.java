package com.carelink.domain.dangerassessment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;

public record DangerAssessmentDeletedEvent(
        DangerAssessmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DangerAssessmentDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
