package com.carelink.domain.careplan.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.common.event.DomainEvent;

public record CarePlanRegisteredEvent(
        CarePlanId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CarePlanRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
