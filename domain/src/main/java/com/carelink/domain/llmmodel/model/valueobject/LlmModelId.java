package com.carelink.domain.llmmodel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record LlmModelId(UUID value) {
    public LlmModelId {
        Objects.requireNonNull(value, "LlmModelId value must not be null");
    }

    public static LlmModelId generate() {
        return new LlmModelId(UUID.randomUUID());
    }
}
