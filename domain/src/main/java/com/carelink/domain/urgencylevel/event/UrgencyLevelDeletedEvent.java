package com.carelink.domain.urgencylevel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public record UrgencyLevelDeletedEvent(
        UrgencyLevelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public UrgencyLevelDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
