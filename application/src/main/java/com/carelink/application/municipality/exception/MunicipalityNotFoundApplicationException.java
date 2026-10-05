package com.carelink.application.municipality.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class MunicipalityNotFoundApplicationException extends NotFoundApplicationException {
    public MunicipalityNotFoundApplicationException(String message) {
        super(message);
    }
}
