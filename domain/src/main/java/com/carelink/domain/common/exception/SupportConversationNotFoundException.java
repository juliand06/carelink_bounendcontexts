package com.carelink.domain.common.exception;

import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public class SupportConversationNotFoundException extends RuntimeException {
    public SupportConversationNotFoundException(SupportConversationId id) {
        super("SupportConversation not found with id: " + id.value());
    }
}
