package com.carelink.domain.common.exception;

import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;

public class DangerAssessmentNotFoundException extends RuntimeException {
    public DangerAssessmentNotFoundException(DangerAssessmentId id) {
        super("DangerAssessment not found with id: " + id.value());
    }
}
