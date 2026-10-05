package com.carelink.infrastructure.supportconversation.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "support_conversations")
public class SupportConversationJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "conversation_status_id", nullable = false)
    private UUID conversationStatusId;

    @Column(name = "urgency_level_id", nullable = false)
    private UUID urgencyLevelId;

    @Column(name = "last_interaction_at", nullable = true)
    private LocalDateTime lastInteractionAt;

    @Column(name = "is_closed", nullable = false)
    private Boolean isClosed;

    @Column(name = "closed_at", nullable = true)
    private LocalDateTime closedAt;

    @Column(name = "closed_by", nullable = true)
    private UUID closedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public SupportConversationJpaEntity() { }

    public SupportConversationJpaEntity(
            UUID id,
            UUID conversationStatusId,
            UUID urgencyLevelId,
            LocalDateTime lastInteractionAt,
            Boolean isClosed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.conversationStatusId = conversationStatusId;
        this.urgencyLevelId = urgencyLevelId;
        this.lastInteractionAt = lastInteractionAt;
        this.isClosed = isClosed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConversationStatusId() { return conversationStatusId; }
    public void setConversationStatusId(UUID conversationStatusId) { this.conversationStatusId = conversationStatusId; }
    public UUID getUrgencyLevelId() { return urgencyLevelId; }
    public void setUrgencyLevelId(UUID urgencyLevelId) { this.urgencyLevelId = urgencyLevelId; }
    public LocalDateTime getLastInteractionAt() { return lastInteractionAt; }
    public void setLastInteractionAt(LocalDateTime lastInteractionAt) { this.lastInteractionAt = lastInteractionAt; }
    public Boolean getIsClosed() { return isClosed; }
    public void setIsClosed(Boolean isClosed) { this.isClosed = isClosed; }
    public LocalDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(LocalDateTime closedAt) { this.closedAt = closedAt; }
    public UUID getClosedBy() { return closedBy; }
    public void setClosedBy(UUID closedBy) { this.closedBy = closedBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
