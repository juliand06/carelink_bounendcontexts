package com.carelink.domain.conversationaiconfig.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigRegisteredEvent;
import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigUpdatedEvent;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public class ConversationAiConfig extends AggregateRoot {
    private final ConversationAiConfigId id;
    private SupportConversationId conversationId;
    private Boolean aiEnabled;
    private LlmModelId preferredModelId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConversationAiConfig(
            ConversationAiConfigId id,
            SupportConversationId conversationId,
            Boolean aiEnabled,
            LlmModelId preferredModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.aiEnabled = Objects.requireNonNull(aiEnabled, "aiEnabled must not be null");
        this.preferredModelId = Objects.requireNonNull(preferredModelId, "preferredModelId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ConversationAiConfig register(
            SupportConversationId conversationId,
            Boolean aiEnabled,
            LlmModelId preferredModelId) {
        ConversationAiConfigId id = ConversationAiConfigId.generate();
        LocalDateTime now = LocalDateTime.now();
        ConversationAiConfig aggregate = new ConversationAiConfig(
                id,
                conversationId,
                aiEnabled,
                preferredModelId,
                now,
                now);
        aggregate.recordEvent(new ConversationAiConfigRegisteredEvent(id, now));
        return aggregate;
    }

    public static ConversationAiConfig restore(
            ConversationAiConfigId id,
            SupportConversationId conversationId,
            Boolean aiEnabled,
            LlmModelId preferredModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConversationAiConfig(
                id,
                conversationId,
                aiEnabled,
                preferredModelId,
                createdAt,
                updatedAt);
    }

    public void update(
            SupportConversationId conversationId,
            Boolean aiEnabled,
            LlmModelId preferredModelId) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.aiEnabled = Objects.requireNonNull(aiEnabled, "aiEnabled must not be null");
        this.preferredModelId = Objects.requireNonNull(preferredModelId, "preferredModelId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ConversationAiConfigUpdatedEvent(
                this.id,
                this.conversationId,
                this.aiEnabled,
                this.preferredModelId,
                now));
    }

    public ConversationAiConfigId id() {
        return id;
    }

    public SupportConversationId conversationId() {
        return conversationId;
    }

    public Boolean aiEnabled() {
        return aiEnabled;
    }

    public LlmModelId preferredModelId() {
        return preferredModelId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
