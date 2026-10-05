package com.carelink.domain.llmexecutionerror.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public record LlmExecutionErrorUpdatedEvent(
        LlmExecutionErrorId id,
        LlmExecutionId executionId,
        String errorDetail,
        String errorCode,
        String vendorErrorRef,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionErrorUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(executionId, "executionId must not be null");
        Objects.requireNonNull(errorDetail, "errorDetail must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
