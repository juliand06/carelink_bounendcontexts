package com.carelink.domain.llmexecutionmetric.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public record LlmExecutionMetricRegisteredEvent(
        LlmExecutionMetricId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionMetricRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
