package com.carelink.application.caregoalstatus.command;

import java.util.Objects;

public record RegisterCareGoalStatusCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterCareGoalStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
