package com.carelink.domain.client.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.common.event.DomainEvent;

public record ClientDeletedEvent(
        ClientId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClientDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
