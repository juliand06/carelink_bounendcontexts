package com.carelink.infrastructure.caregoal.adapters.out.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "care_goals")
public class CareGoalJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "care_plan_id", nullable = false)
    private UUID carePlanId;

    @Column(name = "goal_description", nullable = false, columnDefinition = "text")
    private String goalDescription;

    @Column(name = "target_date", nullable = true)
    private LocalDate targetDate;

    @Column(name = "achieved_at", nullable = true)
    private LocalDateTime achievedAt;

    @Column(name = "progress_notes", nullable = true, columnDefinition = "text")
    private String progressNotes;

    @Column(name = "care_goal_status_id", nullable = false)
    private UUID careGoalStatusId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CareGoalJpaEntity() { }

    public CareGoalJpaEntity(
            UUID id,
            UUID carePlanId,
            String goalDescription,
            LocalDate targetDate,
            LocalDateTime achievedAt,
            String progressNotes,
            UUID careGoalStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.carePlanId = carePlanId;
        this.goalDescription = goalDescription;
        this.targetDate = targetDate;
        this.achievedAt = achievedAt;
        this.progressNotes = progressNotes;
        this.careGoalStatusId = careGoalStatusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getCarePlanId() { return carePlanId; }
    public void setCarePlanId(UUID carePlanId) { this.carePlanId = carePlanId; }
    public String getGoalDescription() { return goalDescription; }
    public void setGoalDescription(String goalDescription) { this.goalDescription = goalDescription; }
    public LocalDate getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }
    public LocalDateTime getAchievedAt() { return achievedAt; }
    public void setAchievedAt(LocalDateTime achievedAt) { this.achievedAt = achievedAt; }
    public String getProgressNotes() { return progressNotes; }
    public void setProgressNotes(String progressNotes) { this.progressNotes = progressNotes; }
    public UUID getCareGoalStatusId() { return careGoalStatusId; }
    public void setCareGoalStatusId(UUID careGoalStatusId) { this.careGoalStatusId = careGoalStatusId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
