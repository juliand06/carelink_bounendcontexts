package com.carelink.application.llmexecutionmetric.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public record UpdateLlmExecutionMetricCommand(
        LlmExecutionMetricId id,
        LlmExecutionId executionId,
        Integer inputTokens,
        Integer outputTokens,
        Integer totalTokens,
        BigDecimal totalCost
) {
    public UpdateLlmExecutionMetricCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(executionId, "executionId must not be null");
        Objects.requireNonNull(inputTokens, "inputTokens must not be null");
        Objects.requireNonNull(outputTokens, "outputTokens must not be null");
        Objects.requireNonNull(totalTokens, "totalTokens must not be null");
        Objects.requireNonNull(totalCost, "totalCost must not be null");
    }
}
