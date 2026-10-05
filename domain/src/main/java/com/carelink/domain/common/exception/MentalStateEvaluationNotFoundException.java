package com.carelink.domain.common.exception;

import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;

public class MentalStateEvaluationNotFoundException extends RuntimeException {
    public MentalStateEvaluationNotFoundException(MentalStateEvaluationId id) {
        super("MentalStateEvaluation not found with id: " + id.value());
    }
}
