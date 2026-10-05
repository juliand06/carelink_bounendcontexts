package com.carelink.application.dangerlevel.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class DangerLevelNotFoundApplicationException extends NotFoundApplicationException {
    public DangerLevelNotFoundApplicationException(String message) {
        super(message);
    }
}
