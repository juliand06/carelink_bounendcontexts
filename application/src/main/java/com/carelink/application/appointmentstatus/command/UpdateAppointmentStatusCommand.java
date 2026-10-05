package com.carelink.application.appointmentstatus.command;

import java.util.Objects;

import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;

public record UpdateAppointmentStatusCommand(
        AppointmentStatusId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateAppointmentStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
