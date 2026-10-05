package com.carelink.application.informedconsenttype.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class InformedConsentTypeNotFoundApplicationException extends NotFoundApplicationException {
    public InformedConsentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
