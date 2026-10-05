package com.carelink.infrastructure.careplan.adapters.out.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "care_plans")
public class CarePlanJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "appointment_id", nullable = false)
    private UUID appointmentId;

    @Column(name = "specialist_id", nullable = false)
    private UUID specialistId;

    @Column(name = "plan_title", nullable = false, length = 200)
    private String planTitle;

    @Column(name = "plan_description", nullable = true, columnDefinition = "text")
    private String planDescription;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "projected_end_date", nullable = true)
    private LocalDate projectedEndDate;

    @Column(name = "care_plan_status_id", nullable = false)
    private UUID carePlanStatusId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CarePlanJpaEntity() { }

    public CarePlanJpaEntity(
            UUID id,
            UUID appointmentId,
            UUID specialistId,
            String planTitle,
            String planDescription,
            LocalDate startDate,
            LocalDate projectedEndDate,
            UUID carePlanStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.specialistId = specialistId;
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.startDate = startDate;
        this.projectedEndDate = projectedEndDate;
        this.carePlanStatusId = carePlanStatusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAppointmentId() { return appointmentId; }
    public void setAppointmentId(UUID appointmentId) { this.appointmentId = appointmentId; }
    public UUID getSpecialistId() { return specialistId; }
    public void setSpecialistId(UUID specialistId) { this.specialistId = specialistId; }
    public String getPlanTitle() { return planTitle; }
    public void setPlanTitle(String planTitle) { this.planTitle = planTitle; }
    public String getPlanDescription() { return planDescription; }
    public void setPlanDescription(String planDescription) { this.planDescription = planDescription; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getProjectedEndDate() { return projectedEndDate; }
    public void setProjectedEndDate(LocalDate projectedEndDate) { this.projectedEndDate = projectedEndDate; }
    public UUID getCarePlanStatusId() { return carePlanStatusId; }
    public void setCarePlanStatusId(UUID carePlanStatusId) { this.carePlanStatusId = carePlanStatusId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
