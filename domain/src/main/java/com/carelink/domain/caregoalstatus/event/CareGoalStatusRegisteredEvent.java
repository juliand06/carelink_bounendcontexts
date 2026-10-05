package com.carelink.domain.caregoalstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.common.event.DomainEvent;

public record CareGoalStatusRegisteredEvent(
        CareGoalStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CareGoalStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
