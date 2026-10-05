package com.carelink.domain.informedconsenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public record InformedConsentTypeRegisteredEvent(
        InformedConsentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public InformedConsentTypeRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
