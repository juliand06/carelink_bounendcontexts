package com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "handoff_status_audit")
public class HandoffStatusAuditJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "handoff_id", nullable = false)
    private UUID handoffId;

    @Column(name = "handoff_status_id", nullable = false)
    private UUID handoffStatusId;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @Column(name = "transitioned_at", nullable = false)
    private LocalDateTime transitionedAt;

    public HandoffStatusAuditJpaEntity() { }

    public HandoffStatusAuditJpaEntity(
            UUID id,
            UUID handoffId,
            UUID handoffStatusId,
            LocalDateTime recordedAt,
            LocalDateTime transitionedAt) {
        this.id = id;
        this.handoffId = handoffId;
        this.handoffStatusId = handoffStatusId;
        this.recordedAt = recordedAt;
        this.transitionedAt = transitionedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getHandoffId() { return handoffId; }
    public void setHandoffId(UUID handoffId) { this.handoffId = handoffId; }
    public UUID getHandoffStatusId() { return handoffStatusId; }
    public void setHandoffStatusId(UUID handoffStatusId) { this.handoffStatusId = handoffStatusId; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
    public LocalDateTime getTransitionedAt() { return transitionedAt; }
    public void setTransitionedAt(LocalDateTime transitionedAt) { this.transitionedAt = transitionedAt; }
}
