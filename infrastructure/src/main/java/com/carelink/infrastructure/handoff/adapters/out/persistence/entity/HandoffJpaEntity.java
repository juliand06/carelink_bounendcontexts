package com.carelink.infrastructure.handoff.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "handoffs")
public class HandoffJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    @Column(name = "handoff_status_id", nullable = false)
    private UUID handoffStatusId;

    @Column(name = "originated_from_ai", nullable = false)
    private Boolean originatedFromAi;

    @Column(name = "handoff_reason", nullable = true, columnDefinition = "text")
    private String handoffReason;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public HandoffJpaEntity() { }

    public HandoffJpaEntity(
            UUID id,
            UUID conversationId,
            UUID handoffStatusId,
            Boolean originatedFromAi,
            String handoffReason,
            LocalDateTime createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.handoffStatusId = handoffStatusId;
        this.originatedFromAi = originatedFromAi;
        this.handoffReason = handoffReason;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }
    public UUID getHandoffStatusId() { return handoffStatusId; }
    public void setHandoffStatusId(UUID handoffStatusId) { this.handoffStatusId = handoffStatusId; }
    public Boolean getOriginatedFromAi() { return originatedFromAi; }
    public void setOriginatedFromAi(Boolean originatedFromAi) { this.originatedFromAi = originatedFromAi; }
    public String getHandoffReason() { return handoffReason; }
    public void setHandoffReason(String handoffReason) { this.handoffReason = handoffReason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
