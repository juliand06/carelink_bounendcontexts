package com.carelink.application.handoffassignment.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class HandoffAssignmentNotFoundApplicationException extends NotFoundApplicationException {
    public HandoffAssignmentNotFoundApplicationException(String message) {
        super(message);
    }
}
