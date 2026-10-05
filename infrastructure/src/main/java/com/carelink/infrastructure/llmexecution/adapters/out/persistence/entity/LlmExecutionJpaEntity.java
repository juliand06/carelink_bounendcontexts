package com.carelink.infrastructure.llmexecution.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "llm_executions")
public class LlmExecutionJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    @Column(name = "message_id", nullable = false)
    private UUID messageId;

    @Column(name = "model_id", nullable = false)
    private UUID modelId;

    @Column(name = "execution_status_id", nullable = false)
    private UUID executionStatusId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public LlmExecutionJpaEntity() { }

    public LlmExecutionJpaEntity(
            UUID id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID executionStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.modelId = modelId;
        this.executionStatusId = executionStatusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }
    public UUID getMessageId() { return messageId; }
    public void setMessageId(UUID messageId) { this.messageId = messageId; }
    public UUID getModelId() { return modelId; }
    public void setModelId(UUID modelId) { this.modelId = modelId; }
    public UUID getExecutionStatusId() { return executionStatusId; }
    public void setExecutionStatusId(UUID executionStatusId) { this.executionStatusId = executionStatusId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
