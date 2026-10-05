package com.carelink.domain.evaluationinstrumenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record EvaluationInstrumentTypeId(UUID value) {
    public EvaluationInstrumentTypeId {
        Objects.requireNonNull(value, "EvaluationInstrumentTypeId value must not be null");
    }

    public static EvaluationInstrumentTypeId generate() {
        return new EvaluationInstrumentTypeId(UUID.randomUUID());
    }
}
