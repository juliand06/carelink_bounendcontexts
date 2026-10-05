package com.carelink.domain.dangerlevel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public record DangerLevelRegisteredEvent(
        DangerLevelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DangerLevelRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
