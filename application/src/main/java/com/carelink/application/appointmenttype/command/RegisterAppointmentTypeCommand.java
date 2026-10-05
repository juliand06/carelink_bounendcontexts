package com.carelink.application.appointmenttype.command;

import java.util.Objects;

public record RegisterAppointmentTypeCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterAppointmentTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
