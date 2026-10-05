package com.carelink.domain.clienttrustedcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public record ClientTrustedContactUpdatedEvent(
        ClientTrustedContactId id,
        ClientId clientId,
        TrustedContactId trustedContactId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClientTrustedContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
