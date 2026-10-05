package com.carelink.infrastructure.llmexecutionmetric.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateLlmExecutionMetricRequest(
        @NotNull(message = "executionId is required")
        UUID executionId,

        @NotNull(message = "inputTokens is required")
        Integer inputTokens,

        @NotNull(message = "outputTokens is required")
        Integer outputTokens,

        @NotNull(message = "totalTokens is required")
        Integer totalTokens,

        @NotNull(message = "totalCost is required")
        BigDecimal totalCost
) {
}
