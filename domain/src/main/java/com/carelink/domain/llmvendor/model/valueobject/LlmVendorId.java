package com.carelink.domain.llmvendor.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record LlmVendorId(UUID value) {
    public LlmVendorId {
        Objects.requireNonNull(value, "LlmVendorId value must not be null");
    }

    public static LlmVendorId generate() {
        return new LlmVendorId(UUID.randomUUID());
    }
}
