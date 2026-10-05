package com.carelink.domain.trustedcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record TrustedContactRegisteredEvent(
        TrustedContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TrustedContactRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
