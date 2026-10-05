package com.carelink.application.handoff.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class HandoffNotFoundApplicationException extends NotFoundApplicationException {
    public HandoffNotFoundApplicationException(String message) {
        super(message);
    }
}
