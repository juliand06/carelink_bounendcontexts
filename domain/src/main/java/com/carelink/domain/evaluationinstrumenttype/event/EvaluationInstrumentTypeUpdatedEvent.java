package com.carelink.domain.evaluationinstrumenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public record EvaluationInstrumentTypeUpdatedEvent(
        EvaluationInstrumentTypeId id,
        String code,
        String label,
        Boolean active,
        String description,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EvaluationInstrumentTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
