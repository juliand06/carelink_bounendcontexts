package com.carelink.application.evaluationinstrumenttype.command;

import java.util.Objects;

import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public record UpdateEvaluationInstrumentTypeCommand(
        EvaluationInstrumentTypeId id,
        String code,
        String label,
        Boolean active,
        String description
) {
    public UpdateEvaluationInstrumentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
