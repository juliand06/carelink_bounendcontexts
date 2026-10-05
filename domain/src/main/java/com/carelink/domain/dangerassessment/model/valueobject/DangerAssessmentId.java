package com.carelink.domain.dangerassessment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DangerAssessmentId(UUID value) {
    public DangerAssessmentId {
        Objects.requireNonNull(value, "DangerAssessmentId value must not be null");
    }

    public static DangerAssessmentId generate() {
        return new DangerAssessmentId(UUID.randomUUID());
    }
}
