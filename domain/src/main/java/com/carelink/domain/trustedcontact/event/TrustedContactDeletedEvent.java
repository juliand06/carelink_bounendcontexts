package com.carelink.domain.trustedcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record TrustedContactDeletedEvent(
        TrustedContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TrustedContactDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
