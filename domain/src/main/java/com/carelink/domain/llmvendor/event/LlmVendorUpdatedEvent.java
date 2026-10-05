package com.carelink.domain.llmvendor.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public record LlmVendorUpdatedEvent(
        LlmVendorId id,
        String brandName,
        String legalName,
        String websiteUrl,
        Boolean isEnabled,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmVendorUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(brandName, "brandName must not be null");
        Objects.requireNonNull(isEnabled, "isEnabled must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
