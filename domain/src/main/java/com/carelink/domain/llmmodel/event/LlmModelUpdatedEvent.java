package com.carelink.domain.llmmodel.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public record LlmModelUpdatedEvent(
        LlmModelId id,
        LlmVendorId llmVendorId,
        String displayName,
        String apiIdentifier,
        BigDecimal pricePerInputToken,
        BigDecimal pricePerOutputToken,
        Integer maxOutputTokens,
        Integer contextLength,
        Boolean isEnabled,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmModelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(llmVendorId, "llmVendorId must not be null");
        Objects.requireNonNull(displayName, "displayName must not be null");
        Objects.requireNonNull(apiIdentifier, "apiIdentifier must not be null");
        Objects.requireNonNull(pricePerInputToken, "pricePerInputToken must not be null");
        Objects.requireNonNull(pricePerOutputToken, "pricePerOutputToken must not be null");
        Objects.requireNonNull(maxOutputTokens, "maxOutputTokens must not be null");
        Objects.requireNonNull(contextLength, "contextLength must not be null");
        Objects.requireNonNull(isEnabled, "isEnabled must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
