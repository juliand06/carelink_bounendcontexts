package com.carelink.domain.security.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.security.model.valueobject.UserId;

public record UserRegisteredEvent(
        UserId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public UserRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
