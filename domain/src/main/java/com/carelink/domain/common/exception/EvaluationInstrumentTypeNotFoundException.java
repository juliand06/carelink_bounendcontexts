package com.carelink.domain.common.exception;

import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public class EvaluationInstrumentTypeNotFoundException extends RuntimeException {
    public EvaluationInstrumentTypeNotFoundException(EvaluationInstrumentTypeId id) {
        super("EvaluationInstrumentType not found with id: " + id.value());
    }
}
