package com.carelink.application.specialist.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SpecialistNotFoundApplicationException extends NotFoundApplicationException {
    public SpecialistNotFoundApplicationException(String message) {
        super(message);
    }
}
