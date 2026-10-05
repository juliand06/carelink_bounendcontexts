package com.carelink.application.appointmentstatus.command;

import java.util.Objects;

public record RegisterAppointmentStatusCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterAppointmentStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
