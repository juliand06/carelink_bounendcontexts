package com.carelink.domain.appointmenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AppointmentTypeId(UUID value) {
    public AppointmentTypeId {
        Objects.requireNonNull(value, "AppointmentTypeId value must not be null");
    }

    public static AppointmentTypeId generate() {
        return new AppointmentTypeId(UUID.randomUUID());
    }
}
