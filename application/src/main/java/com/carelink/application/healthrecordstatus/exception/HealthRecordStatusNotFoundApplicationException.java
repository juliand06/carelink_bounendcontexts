package com.carelink.application.healthrecordstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class HealthRecordStatusNotFoundApplicationException extends NotFoundApplicationException {
    public HealthRecordStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
