package com.carelink.domain.evaluationinstrumenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public record EvaluationInstrumentTypeRegisteredEvent(
        EvaluationInstrumentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EvaluationInstrumentTypeRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
