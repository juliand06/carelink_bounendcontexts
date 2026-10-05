package com.carelink.application.clientallergy.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ClientAllergyNotFoundApplicationException extends NotFoundApplicationException {
    public ClientAllergyNotFoundApplicationException(String message) {
        super(message);
    }
}
