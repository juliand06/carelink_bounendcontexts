package com.carelink.application.conversationaiconfig.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ConversationAiConfigNotFoundApplicationException extends NotFoundApplicationException {
    public ConversationAiConfigNotFoundApplicationException(String message) {
        super(message);
    }
}
