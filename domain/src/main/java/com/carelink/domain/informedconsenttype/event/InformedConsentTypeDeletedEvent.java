package com.carelink.domain.informedconsenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public record InformedConsentTypeDeletedEvent(
        InformedConsentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public InformedConsentTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
