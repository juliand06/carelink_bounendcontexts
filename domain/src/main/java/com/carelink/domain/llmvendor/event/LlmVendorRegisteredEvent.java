package com.carelink.domain.llmvendor.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public record LlmVendorRegisteredEvent(
        LlmVendorId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmVendorRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
