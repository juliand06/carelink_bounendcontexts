package com.carelink.domain.common.exception;

import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;

public class ConversationAiConfigNotFoundException extends RuntimeException {
    public ConversationAiConfigNotFoundException(ConversationAiConfigId id) {
        super("ConversationAiConfig not found with id: " + id.value());
    }
}
