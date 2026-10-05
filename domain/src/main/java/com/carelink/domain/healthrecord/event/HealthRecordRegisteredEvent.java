package com.carelink.domain.healthrecord.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;

public record HealthRecordRegisteredEvent(
        HealthRecordId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HealthRecordRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
