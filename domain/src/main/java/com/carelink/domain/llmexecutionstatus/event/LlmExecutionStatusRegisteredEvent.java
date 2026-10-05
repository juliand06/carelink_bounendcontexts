package com.carelink.domain.llmexecutionstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public record LlmExecutionStatusRegisteredEvent(
        LlmExecutionStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
