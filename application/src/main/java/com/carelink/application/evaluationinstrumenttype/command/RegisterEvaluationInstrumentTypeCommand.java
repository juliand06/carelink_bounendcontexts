package com.carelink.application.evaluationinstrumenttype.command;

import java.util.Objects;

public record RegisterEvaluationInstrumentTypeCommand(
        String code,
        String label,
        Boolean active,
        String description
) {
    public RegisterEvaluationInstrumentTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
