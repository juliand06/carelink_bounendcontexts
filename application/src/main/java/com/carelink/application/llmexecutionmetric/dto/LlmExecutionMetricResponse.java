package com.carelink.application.llmexecutionmetric.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LlmExecutionMetricResponse(
        UUID id,
        UUID executionId,
        Integer inputTokens,
        Integer outputTokens,
        Integer totalTokens,
        BigDecimal totalCost,
        LocalDateTime createdAt
) {
}
