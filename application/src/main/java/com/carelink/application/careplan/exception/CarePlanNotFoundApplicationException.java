package com.carelink.application.careplan.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class CarePlanNotFoundApplicationException extends NotFoundApplicationException {
    public CarePlanNotFoundApplicationException(String message) {
        super(message);
    }
}
