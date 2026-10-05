package com.carelink.domain.conversationaiconfig.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ConversationAiConfigId(UUID value) {
    public ConversationAiConfigId {
        Objects.requireNonNull(value, "ConversationAiConfigId value must not be null");
    }

    public static ConversationAiConfigId generate() {
        return new ConversationAiConfigId(UUID.randomUUID());
    }
}
