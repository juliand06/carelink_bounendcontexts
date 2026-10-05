package com.carelink.domain.informedconsenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public record InformedConsentTypeUpdatedEvent(
        InformedConsentTypeId id,
        String code,
        String label,
        Boolean active,
        String description,
        LocalDateTime occurredOn
) implements DomainEvent {
    public InformedConsentTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
