package com.carelink.domain.appointmentstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.common.event.DomainEvent;

public record AppointmentStatusDeletedEvent(
        AppointmentStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AppointmentStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
