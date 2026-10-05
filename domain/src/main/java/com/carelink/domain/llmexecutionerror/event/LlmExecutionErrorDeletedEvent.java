package com.carelink.domain.llmexecutionerror.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public record LlmExecutionErrorDeletedEvent(
        LlmExecutionErrorId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionErrorDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
