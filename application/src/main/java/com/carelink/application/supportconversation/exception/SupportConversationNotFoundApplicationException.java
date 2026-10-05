package com.carelink.application.supportconversation.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class SupportConversationNotFoundApplicationException extends NotFoundApplicationException {
    public SupportConversationNotFoundApplicationException(String message) {
        super(message);
    }
}
