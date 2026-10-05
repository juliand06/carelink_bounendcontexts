package com.carelink.domain.clienttrustedcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.common.event.DomainEvent;

public record ClientTrustedContactRegisteredEvent(
        ClientTrustedContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClientTrustedContactRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
