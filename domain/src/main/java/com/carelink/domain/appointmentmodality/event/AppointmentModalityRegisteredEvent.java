package com.carelink.domain.appointmentmodality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.common.event.DomainEvent;

public record AppointmentModalityRegisteredEvent(
        AppointmentModalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AppointmentModalityRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
