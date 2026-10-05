package com.carelink.domain.common.exception;

import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public class LlmExecutionMetricNotFoundException extends RuntimeException {
    public LlmExecutionMetricNotFoundException(LlmExecutionMetricId id) {
        super("LlmExecutionMetric not found with id: " + id.value());
    }
}
