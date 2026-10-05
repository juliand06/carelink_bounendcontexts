package com.carelink.application.informedconsenttype.command;

import java.util.Objects;

public record RegisterInformedConsentTypeCommand(
        String code,
        String label,
        Boolean active,
        String description
) {
    public RegisterInformedConsentTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
