package com.carelink.application.urgencylevel.command;

import java.util.Objects;

public record RegisterUrgencyLevelCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterUrgencyLevelCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
