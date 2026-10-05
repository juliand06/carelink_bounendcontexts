package com.carelink.domain.llmmodel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;

public record LlmModelRegisteredEvent(
        LlmModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmModelRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
