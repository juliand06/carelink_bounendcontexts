package com.carelink.application.supportconversationstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SupportConversationStatusNotFoundApplicationException extends NotFoundApplicationException {
    public SupportConversationStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
