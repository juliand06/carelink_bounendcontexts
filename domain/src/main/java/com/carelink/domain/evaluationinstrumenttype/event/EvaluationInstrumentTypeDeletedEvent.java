package com.carelink.domain.evaluationinstrumenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public record EvaluationInstrumentTypeDeletedEvent(
        EvaluationInstrumentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EvaluationInstrumentTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
