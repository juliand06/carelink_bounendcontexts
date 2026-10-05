package com.carelink.application.specialistcredential.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SpecialistCredentialNotFoundApplicationException extends NotFoundApplicationException {
    public SpecialistCredentialNotFoundApplicationException(String message) {
        super(message);
    }
}
