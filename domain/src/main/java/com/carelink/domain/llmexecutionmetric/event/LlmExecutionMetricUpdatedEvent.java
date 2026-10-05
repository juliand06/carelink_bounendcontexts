package com.carelink.domain.llmexecutionmetric.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public record LlmExecutionMetricUpdatedEvent(
        LlmExecutionMetricId id,
        LlmExecutionId executionId,
        Integer inputTokens,
        Integer outputTokens,
        Integer totalTokens,
        BigDecimal totalCost,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionMetricUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(executionId, "executionId must not be null");
        Objects.requireNonNull(inputTokens, "inputTokens must not be null");
        Objects.requireNonNull(outputTokens, "outputTokens must not be null");
        Objects.requireNonNull(totalTokens, "totalTokens must not be null");
        Objects.requireNonNull(totalCost, "totalCost must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
