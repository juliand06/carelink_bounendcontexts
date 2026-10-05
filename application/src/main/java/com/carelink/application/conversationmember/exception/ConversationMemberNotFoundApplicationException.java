package com.carelink.application.conversationmember.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class ConversationMemberNotFoundApplicationException extends NotFoundApplicationException {
    public ConversationMemberNotFoundApplicationException(String message) {
        super(message);
    }
}
