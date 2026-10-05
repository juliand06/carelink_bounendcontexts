package com.carelink.domain.appointmentmodality.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AppointmentModalityId(UUID value) {
    public AppointmentModalityId {
        Objects.requireNonNull(value, "AppointmentModalityId value must not be null");
    }

    public static AppointmentModalityId generate() {
        return new AppointmentModalityId(UUID.randomUUID());
    }
}
