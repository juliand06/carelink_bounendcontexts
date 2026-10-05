package com.carelink.domain.supportconversation.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record SupportConversationRegisteredEvent(
        SupportConversationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SupportConversationRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
