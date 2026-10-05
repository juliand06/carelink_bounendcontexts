package com.carelink.infrastructure.appointment.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class AppointmentJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "health_record_id", nullable = false)
    private UUID healthRecordId;

    @Column(name = "specialist_id", nullable = false)
    private UUID specialistId;

    @Column(name = "appointment_type_id", nullable = false)
    private UUID appointmentTypeId;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at", nullable = true)
    private LocalDateTime endedAt;

    @Column(name = "chief_complaint", nullable = true, columnDefinition = "text")
    private String chiefComplaint;

    @Column(name = "current_condition", nullable = true, columnDefinition = "text")
    private String currentCondition;

    @Column(name = "modality_id", nullable = false)
    private UUID modalityId;

    @Column(name = "status_id", nullable = false)
    private UUID statusId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", nullable = true)
    private UUID updatedBy;

    public AppointmentJpaEntity() { }

    public AppointmentJpaEntity(
            UUID id,
            UUID healthRecordId,
            UUID specialistId,
            UUID appointmentTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String chiefComplaint,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        this.id = id;
        this.healthRecordId = healthRecordId;
        this.specialistId = specialistId;
        this.appointmentTypeId = appointmentTypeId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.chiefComplaint = chiefComplaint;
        this.currentCondition = currentCondition;
        this.modalityId = modalityId;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getHealthRecordId() { return healthRecordId; }
    public void setHealthRecordId(UUID healthRecordId) { this.healthRecordId = healthRecordId; }
    public UUID getSpecialistId() { return specialistId; }
    public void setSpecialistId(UUID specialistId) { this.specialistId = specialistId; }
    public UUID getAppointmentTypeId() { return appointmentTypeId; }
    public void setAppointmentTypeId(UUID appointmentTypeId) { this.appointmentTypeId = appointmentTypeId; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }
    public String getChiefComplaint() { return chiefComplaint; }
    public void setChiefComplaint(String chiefComplaint) { this.chiefComplaint = chiefComplaint; }
    public String getCurrentCondition() { return currentCondition; }
    public void setCurrentCondition(String currentCondition) { this.currentCondition = currentCondition; }
    public UUID getModalityId() { return modalityId; }
    public void setModalityId(UUID modalityId) { this.modalityId = modalityId; }
    public UUID getStatusId() { return statusId; }
    public void setStatusId(UUID statusId) { this.statusId = statusId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public UUID getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(UUID updatedBy) { this.updatedBy = updatedBy; }
}
