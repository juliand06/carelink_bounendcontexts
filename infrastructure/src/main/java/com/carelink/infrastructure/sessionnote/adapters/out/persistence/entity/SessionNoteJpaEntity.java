package com.carelink.infrastructure.sessionnote.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "session_notes")
public class SessionNoteJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "appointment_id", nullable = false)
    private UUID appointmentId;

    @Column(name = "specialist_id", nullable = false)
    private UUID specialistId;

    @Column(name = "subjective", nullable = true, columnDefinition = "text")
    private String subjective;

    @Column(name = "objective", nullable = true, columnDefinition = "text")
    private String objective;

    @Column(name = "assessment", nullable = true, columnDefinition = "text")
    private String assessment;

    @Column(name = "plan", nullable = true, columnDefinition = "text")
    private String plan;

    @Column(name = "supplementary_notes", nullable = true, columnDefinition = "text")
    private String supplementaryNotes;

    @Column(name = "signed_at", nullable = true)
    private LocalDateTime signedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public SessionNoteJpaEntity() { }

    public SessionNoteJpaEntity(
            UUID id,
            UUID appointmentId,
            UUID specialistId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String supplementaryNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.specialistId = specialistId;
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.supplementaryNotes = supplementaryNotes;
        this.signedAt = signedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAppointmentId() { return appointmentId; }
    public void setAppointmentId(UUID appointmentId) { this.appointmentId = appointmentId; }
    public UUID getSpecialistId() { return specialistId; }
    public void setSpecialistId(UUID specialistId) { this.specialistId = specialistId; }
    public String getSubjective() { return subjective; }
    public void setSubjective(String subjective) { this.subjective = subjective; }
    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }
    public String getAssessment() { return assessment; }
    public void setAssessment(String assessment) { this.assessment = assessment; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
    public String getSupplementaryNotes() { return supplementaryNotes; }
    public void setSupplementaryNotes(String supplementaryNotes) { this.supplementaryNotes = supplementaryNotes; }
    public LocalDateTime getSignedAt() { return signedAt; }
    public void setSignedAt(LocalDateTime signedAt) { this.signedAt = signedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
