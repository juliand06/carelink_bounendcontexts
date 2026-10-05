package com.carelink.domain.contactemailaddress.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;

public record ContactEmailAddressDeletedEvent(
        ContactEmailAddressId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactEmailAddressDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
