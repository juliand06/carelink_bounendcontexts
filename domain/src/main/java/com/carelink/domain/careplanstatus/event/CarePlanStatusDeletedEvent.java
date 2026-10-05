package com.carelink.domain.careplanstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.common.event.DomainEvent;

public record CarePlanStatusDeletedEvent(
        CarePlanStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CarePlanStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
