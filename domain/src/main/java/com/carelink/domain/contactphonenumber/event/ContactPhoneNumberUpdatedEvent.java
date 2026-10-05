package com.carelink.domain.contactphonenumber.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record ContactPhoneNumberUpdatedEvent(
        ContactPhoneNumberId id,
        TrustedContactId trustedContactId,
        String phoneNumber,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactPhoneNumberUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
