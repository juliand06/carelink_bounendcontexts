package com.carelink.application.iddocumenttype.command;

import java.util.Objects;

public record RegisterIdDocumentTypeCommand(
        String code,
        String label,
        Boolean active
) {
    public RegisterIdDocumentTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
