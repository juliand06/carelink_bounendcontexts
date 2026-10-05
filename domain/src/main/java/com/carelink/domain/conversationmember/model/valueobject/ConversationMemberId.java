package com.carelink.domain.conversationmember.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ConversationMemberId(UUID value) {
    public ConversationMemberId {
        Objects.requireNonNull(value, "ConversationMemberId value must not be null");
    }

    public static ConversationMemberId generate() {
        return new ConversationMemberId(UUID.randomUUID());
    }
}
