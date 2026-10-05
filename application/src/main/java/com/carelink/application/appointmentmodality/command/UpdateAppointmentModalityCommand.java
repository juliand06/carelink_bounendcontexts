package com.carelink.application.appointmentmodality.command;

import java.util.Objects;

import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;

public record UpdateAppointmentModalityCommand(
        AppointmentModalityId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateAppointmentModalityCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
