package com.carelink.domain.appointmenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.common.event.DomainEvent;

public record AppointmentTypeDeletedEvent(
        AppointmentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AppointmentTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
