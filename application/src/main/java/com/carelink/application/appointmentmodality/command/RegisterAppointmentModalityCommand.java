package com.carelink.application.appointmentmodality.command;

import java.util.Objects;

public record RegisterAppointmentModalityCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterAppointmentModalityCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
