package com.carelink.application.trustedcontact.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class TrustedContactNotFoundApplicationException extends NotFoundApplicationException {
    public TrustedContactNotFoundApplicationException(String message) {
        super(message);
    }
}
