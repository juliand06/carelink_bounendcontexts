package com.carelink.domain.contactrelationshiptype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public record ContactRelationshipTypeRegisteredEvent(
        ContactRelationshipTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactRelationshipTypeRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
