package com.carelink.domain.specialist.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record SpecialistDeletedEvent(
        SpecialistId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SpecialistDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
