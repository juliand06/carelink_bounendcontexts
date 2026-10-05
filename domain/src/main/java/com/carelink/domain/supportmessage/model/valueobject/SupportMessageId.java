package com.carelink.domain.supportmessage.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SupportMessageId(UUID value) {
    public SupportMessageId {
        Objects.requireNonNull(value, "SupportMessageId value must not be null");
    }

    public static SupportMessageId generate() {
        return new SupportMessageId(UUID.randomUUID());
    }
}
