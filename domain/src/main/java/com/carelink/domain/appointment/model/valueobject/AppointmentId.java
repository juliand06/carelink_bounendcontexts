package com.carelink.domain.appointment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AppointmentId(UUID value) {
    public AppointmentId {
        Objects.requireNonNull(value, "AppointmentId value must not be null");
    }

    public static AppointmentId generate() {
        return new AppointmentId(UUID.randomUUID());
    }
}
