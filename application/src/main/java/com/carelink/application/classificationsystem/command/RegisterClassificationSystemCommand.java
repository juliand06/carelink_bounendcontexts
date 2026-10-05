package com.carelink.application.classificationsystem.command;

import java.util.Objects;

public record RegisterClassificationSystemCommand(
        String code,
        String label,
        Boolean active,
        String edition
) {
    public RegisterClassificationSystemCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
