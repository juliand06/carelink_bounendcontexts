package com.carelink.domain.healthrecordstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public record HealthRecordStatusRegisteredEvent(
        HealthRecordStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public HealthRecordStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
