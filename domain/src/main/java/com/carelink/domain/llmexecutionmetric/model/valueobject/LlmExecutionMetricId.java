package com.carelink.domain.llmexecutionmetric.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record LlmExecutionMetricId(UUID value) {
    public LlmExecutionMetricId {
        Objects.requireNonNull(value, "LlmExecutionMetricId value must not be null");
    }

    public static LlmExecutionMetricId generate() {
        return new LlmExecutionMetricId(UUID.randomUUID());
    }
}
