package com.carelink.domain.llmexecutionstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public record LlmExecutionStatusUpdatedEvent(
        LlmExecutionStatusId id,
        String label,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
