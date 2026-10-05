package com.carelink.domain.trustedcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record TrustedContactUpdatedEvent(
        TrustedContactId id,
        String fullName,
        ContactRelationshipTypeId relationshipTypeId,
        Boolean isPrimaryContact,
        String notes,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TrustedContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        Objects.requireNonNull(isPrimaryContact, "isPrimaryContact must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
