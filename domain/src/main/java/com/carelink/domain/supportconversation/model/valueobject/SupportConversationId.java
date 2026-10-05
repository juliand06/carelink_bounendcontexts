package com.carelink.domain.supportconversation.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SupportConversationId(UUID value) {
    public SupportConversationId {
        Objects.requireNonNull(value, "SupportConversationId value must not be null");
    }

    public static SupportConversationId generate() {
        return new SupportConversationId(UUID.randomUUID());
    }
}
