package com.carelink.domain.conversationaiconfig.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;

public record ConversationAiConfigRegisteredEvent(
        ConversationAiConfigId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConversationAiConfigRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
