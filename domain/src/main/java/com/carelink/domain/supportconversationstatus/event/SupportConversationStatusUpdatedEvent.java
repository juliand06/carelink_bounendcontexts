package com.carelink.domain.supportconversationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public record SupportConversationStatusUpdatedEvent(
        SupportConversationStatusId id,
        String code,
        String label,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SupportConversationStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
