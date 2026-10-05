package com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "conversation_ai_configs", uniqueConstraints = {
        @UniqueConstraint(name = "uk_conversation_ai_configs_conversation_id", columnNames = "conversation_id")
})
public class ConversationAiConfigJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    @Column(name = "ai_enabled", nullable = false)
    private Boolean aiEnabled;

    @Column(name = "preferred_model_id", nullable = false)
    private UUID preferredModelId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ConversationAiConfigJpaEntity() { }

    public ConversationAiConfigJpaEntity(
            UUID id,
            UUID conversationId,
            Boolean aiEnabled,
            UUID preferredModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.aiEnabled = aiEnabled;
        this.preferredModelId = preferredModelId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }
    public Boolean getAiEnabled() { return aiEnabled; }
    public void setAiEnabled(Boolean aiEnabled) { this.aiEnabled = aiEnabled; }
    public UUID getPreferredModelId() { return preferredModelId; }
    public void setPreferredModelId(UUID preferredModelId) { this.preferredModelId = preferredModelId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
