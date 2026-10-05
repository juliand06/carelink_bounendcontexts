package com.carelink.domain.llmexecutionstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record LlmExecutionStatusId(UUID value) {
    public LlmExecutionStatusId {
        Objects.requireNonNull(value, "LlmExecutionStatusId value must not be null");
    }

    public static LlmExecutionStatusId generate() {
        return new LlmExecutionStatusId(UUID.randomUUID());
    }
}
