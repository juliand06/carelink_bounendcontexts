package com.carelink.domain.common.exception;

import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;

public class HandoffAssignmentNotFoundException extends RuntimeException {
    public HandoffAssignmentNotFoundException(HandoffAssignmentId id) {
        super("HandoffAssignment not found with id: " + id.value());
    }
}
