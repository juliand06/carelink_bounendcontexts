package com.carelink.application.handoffstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class HandoffStatusNotFoundApplicationException extends NotFoundApplicationException {
    public HandoffStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
