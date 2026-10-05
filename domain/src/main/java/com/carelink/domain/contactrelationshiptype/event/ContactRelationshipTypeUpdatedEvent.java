package com.carelink.domain.contactrelationshiptype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public record ContactRelationshipTypeUpdatedEvent(
        ContactRelationshipTypeId id,
        String label,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactRelationshipTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
