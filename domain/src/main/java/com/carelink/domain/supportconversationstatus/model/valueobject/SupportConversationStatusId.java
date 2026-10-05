package com.carelink.domain.supportconversationstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SupportConversationStatusId(UUID value) {
    public SupportConversationStatusId {
        Objects.requireNonNull(value, "SupportConversationStatusId value must not be null");
    }

    public static SupportConversationStatusId generate() {
        return new SupportConversationStatusId(UUID.randomUUID());
    }
}
