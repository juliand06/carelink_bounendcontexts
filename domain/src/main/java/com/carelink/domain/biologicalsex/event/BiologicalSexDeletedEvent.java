package com.carelink.domain.biologicalsex.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.common.event.DomainEvent;

public record BiologicalSexDeletedEvent(
        BiologicalSexId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public BiologicalSexDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
