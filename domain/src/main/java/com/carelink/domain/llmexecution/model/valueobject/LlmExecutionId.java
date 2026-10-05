package com.carelink.domain.llmexecution.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record LlmExecutionId(UUID value) {
    public LlmExecutionId {
        Objects.requireNonNull(value, "LlmExecutionId value must not be null");
    }

    public static LlmExecutionId generate() {
        return new LlmExecutionId(UUID.randomUUID());
    }
}
