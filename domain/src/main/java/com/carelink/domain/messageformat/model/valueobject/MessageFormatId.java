package com.carelink.domain.messageformat.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MessageFormatId(UUID value) {
    public MessageFormatId {
        Objects.requireNonNull(value, "MessageFormatId value must not be null");
    }

    public static MessageFormatId generate() {
        return new MessageFormatId(UUID.randomUUID());
    }
}
