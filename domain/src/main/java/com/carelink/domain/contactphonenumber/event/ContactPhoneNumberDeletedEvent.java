package com.carelink.domain.contactphonenumber.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;

public record ContactPhoneNumberDeletedEvent(
        ContactPhoneNumberId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactPhoneNumberDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
