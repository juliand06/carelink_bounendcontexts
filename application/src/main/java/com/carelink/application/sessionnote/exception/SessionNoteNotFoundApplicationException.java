package com.carelink.application.sessionnote.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SessionNoteNotFoundApplicationException extends NotFoundApplicationException {
    public SessionNoteNotFoundApplicationException(String message) {
        super(message);
    }
}
