package com.carelink.domain.urgencylevel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public record UrgencyLevelRegisteredEvent(
        UrgencyLevelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public UrgencyLevelRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
