package com.carelink.application.urgencylevel.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class UrgencyLevelNotFoundApplicationException extends NotFoundApplicationException {
    public UrgencyLevelNotFoundApplicationException(String message) {
        super(message);
    }
}
