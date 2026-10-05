package com.carelink.application.specialistrole.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SpecialistRoleNotFoundApplicationException extends NotFoundApplicationException {
    public SpecialistRoleNotFoundApplicationException(String message) {
        super(message);
    }
}
