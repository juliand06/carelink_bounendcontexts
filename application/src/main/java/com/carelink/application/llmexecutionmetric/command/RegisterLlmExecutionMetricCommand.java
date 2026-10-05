package com.carelink.application.llmexecutionmetric.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;

public record RegisterLlmExecutionMetricCommand(
        LlmExecutionId executionId,
        Integer inputTokens,
        Integer outputTokens,
        Integer totalTokens,
        BigDecimal totalCost
) {
    public RegisterLlmExecutionMetricCommand {
        Objects.requireNonNull(executionId, "executionId must not be null");
        Objects.requireNonNull(inputTokens, "inputTokens must not be null");
        Objects.requireNonNull(outputTokens, "outputTokens must not be null");
        Objects.requireNonNull(totalTokens, "totalTokens must not be null");
        Objects.requireNonNull(totalCost, "totalCost must not be null");
    }
}
