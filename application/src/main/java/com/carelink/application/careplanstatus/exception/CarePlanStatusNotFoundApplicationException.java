package com.carelink.application.careplanstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class CarePlanStatusNotFoundApplicationException extends NotFoundApplicationException {
    public CarePlanStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
