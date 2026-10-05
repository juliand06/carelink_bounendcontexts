package com.carelink.application.appointmenttype.command;

import java.util.Objects;

import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;

public record UpdateAppointmentTypeCommand(
        AppointmentTypeId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateAppointmentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
