package com.carelink.application.healthrecord.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class HealthRecordNotFoundApplicationException extends NotFoundApplicationException {
    public HealthRecordNotFoundApplicationException(String message) {
        super(message);
    }
}
