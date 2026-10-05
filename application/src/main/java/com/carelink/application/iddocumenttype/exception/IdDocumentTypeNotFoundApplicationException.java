package com.carelink.application.iddocumenttype.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class IdDocumentTypeNotFoundApplicationException extends NotFoundApplicationException {
    public IdDocumentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
