package com.carelink.application.careplanstatus.command;

import java.util.Objects;

public record RegisterCarePlanStatusCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterCarePlanStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
