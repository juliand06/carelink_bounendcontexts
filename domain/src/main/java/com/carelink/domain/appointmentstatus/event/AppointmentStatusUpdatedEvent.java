package com.carelink.domain.appointmentstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.common.event.DomainEvent;

public record AppointmentStatusUpdatedEvent(
        AppointmentStatusId id,
        String code,
        String label,
        Boolean active,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AppointmentStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
