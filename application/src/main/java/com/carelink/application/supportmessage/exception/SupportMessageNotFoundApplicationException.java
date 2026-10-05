package com.carelink.application.supportmessage.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SupportMessageNotFoundApplicationException extends NotFoundApplicationException {
    public SupportMessageNotFoundApplicationException(String message) {
        super(message);
    }
}
