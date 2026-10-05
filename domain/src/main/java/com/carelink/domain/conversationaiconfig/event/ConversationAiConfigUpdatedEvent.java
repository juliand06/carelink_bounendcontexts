package com.carelink.domain.conversationaiconfig.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record ConversationAiConfigUpdatedEvent(
        ConversationAiConfigId id,
        SupportConversationId conversationId,
        Boolean aiEnabled,
        LlmModelId preferredModelId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConversationAiConfigUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(aiEnabled, "aiEnabled must not be null");
        Objects.requireNonNull(preferredModelId, "preferredModelId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
