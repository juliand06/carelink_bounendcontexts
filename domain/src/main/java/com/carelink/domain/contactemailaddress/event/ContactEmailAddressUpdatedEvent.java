package com.carelink.domain.contactemailaddress.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record ContactEmailAddressUpdatedEvent(
        ContactEmailAddressId id,
        TrustedContactId trustedContactId,
        String emailAddress,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactEmailAddressUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(emailAddress, "emailAddress must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
