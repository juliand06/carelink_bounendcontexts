package com.carelink.application.classificationsystem.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ClassificationSystemNotFoundApplicationException extends NotFoundApplicationException {
    public ClassificationSystemNotFoundApplicationException(String message) {
        super(message);
    }
}
