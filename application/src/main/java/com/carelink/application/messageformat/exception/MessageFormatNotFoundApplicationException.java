package com.carelink.application.messageformat.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class MessageFormatNotFoundApplicationException extends NotFoundApplicationException {
    public MessageFormatNotFoundApplicationException(String message) {
        super(message);
    }
}
