package com.carelink.domain.appointmenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.common.event.DomainEvent;

public record AppointmentTypeUpdatedEvent(
        AppointmentTypeId id,
        String code,
        String label,
        Boolean active,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AppointmentTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
