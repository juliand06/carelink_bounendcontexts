package com.carelink.domain.appointment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.event.DomainEvent;

public record AppointmentDeletedEvent(
        AppointmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AppointmentDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
