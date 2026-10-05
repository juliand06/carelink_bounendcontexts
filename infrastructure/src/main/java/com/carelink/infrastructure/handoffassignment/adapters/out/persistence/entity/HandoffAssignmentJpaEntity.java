package com.carelink.infrastructure.handoffassignment.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "handoff_assignments", uniqueConstraints = {
        @UniqueConstraint(name = "uk_handoff_assignments_handoff_id_specialist_id", columnNames = {"handoff_id", "specialist_id"})
})
public class HandoffAssignmentJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "handoff_id", nullable = false)
    private UUID handoffId;

    @Column(name = "specialist_id", nullable = false)
    private UUID specialistId;

    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;

    public HandoffAssignmentJpaEntity() { }

    public HandoffAssignmentJpaEntity(
            UUID id,
            UUID handoffId,
            UUID specialistId,
            LocalDateTime assignedAt) {
        this.id = id;
        this.handoffId = handoffId;
        this.specialistId = specialistId;
        this.assignedAt = assignedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getHandoffId() { return handoffId; }
    public void setHandoffId(UUID handoffId) { this.handoffId = handoffId; }
    public UUID getSpecialistId() { return specialistId; }
    public void setSpecialistId(UUID specialistId) { this.specialistId = specialistId; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}
