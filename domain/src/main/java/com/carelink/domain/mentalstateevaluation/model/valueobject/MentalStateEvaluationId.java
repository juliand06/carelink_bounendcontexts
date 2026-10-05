package com.carelink.domain.mentalstateevaluation.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MentalStateEvaluationId(UUID value) {
    public MentalStateEvaluationId {
        Objects.requireNonNull(value, "MentalStateEvaluationId value must not be null");
    }

    public static MentalStateEvaluationId generate() {
        return new MentalStateEvaluationId(UUID.randomUUID());
    }
}
