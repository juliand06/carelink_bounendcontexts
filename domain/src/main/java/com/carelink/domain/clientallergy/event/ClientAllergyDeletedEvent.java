package com.carelink.domain.clientallergy.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.common.event.DomainEvent;

public record ClientAllergyDeletedEvent(
        ClientAllergyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClientAllergyDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
