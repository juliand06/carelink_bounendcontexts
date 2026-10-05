package com.carelink.domain.appointmentstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AppointmentStatusId(UUID value) {
    public AppointmentStatusId {
        Objects.requireNonNull(value, "AppointmentStatusId value must not be null");
    }

    public static AppointmentStatusId generate() {
        return new AppointmentStatusId(UUID.randomUUID());
    }
}
