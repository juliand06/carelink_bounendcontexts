package com.carelink.domain.caregoal.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.common.event.DomainEvent;

public record CareGoalDeletedEvent(
        CareGoalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CareGoalDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
