package com.carelink.domain.llmexecutionerror.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record LlmExecutionErrorId(UUID value) {
    public LlmExecutionErrorId {
        Objects.requireNonNull(value, "LlmExecutionErrorId value must not be null");
    }

    public static LlmExecutionErrorId generate() {
        return new LlmExecutionErrorId(UUID.randomUUID());
    }
}
