package com.carelink.domain.llmexecution.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;

public record LlmExecutionDeletedEvent(
        LlmExecutionId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
