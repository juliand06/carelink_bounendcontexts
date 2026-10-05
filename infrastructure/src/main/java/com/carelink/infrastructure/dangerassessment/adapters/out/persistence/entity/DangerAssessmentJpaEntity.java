package com.carelink.infrastructure.dangerassessment.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "danger_assessments")
public class DangerAssessmentJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "appointment_id", nullable = false)
    private UUID appointmentId;

    @Column(name = "danger_level_id", nullable = false)
    private UUID dangerLevelId;

    @Column(name = "suicidal_ideation", nullable = false)
    private Boolean suicidalIdeation;

    @Column(name = "has_suicide_plan", nullable = false)
    private Boolean hasSuicidePlan;

    @Column(name = "has_suicide_intent", nullable = false)
    private Boolean hasSuicideIntent;

    @Column(name = "self_harm_behavior", nullable = false)
    private Boolean selfHarmBehavior;

    @Column(name = "risk_to_others", nullable = false)
    private Boolean riskToOthers;

    @Column(name = "protective_factors", nullable = true, columnDefinition = "text")
    private String protectiveFactors;

    @Column(name = "identified_risk_factors", nullable = true, columnDefinition = "text")
    private String identifiedRiskFactors;

    @Column(name = "clinical_actions_taken", nullable = true, columnDefinition = "text")
    private String clinicalActionsTaken;

    @Column(name = "evaluator_observations", nullable = true, columnDefinition = "text")
    private String evaluatorObservations;

    @Column(name = "evaluated_at", nullable = false)
    private LocalDateTime evaluatedAt;

    @Column(name = "evaluated_by", nullable = false)
    private UUID evaluatedBy;

    public DangerAssessmentJpaEntity() { }

    public DangerAssessmentJpaEntity(
            UUID id,
            UUID appointmentId,
            UUID dangerLevelId,
            Boolean suicidalIdeation,
            Boolean hasSuicidePlan,
            Boolean hasSuicideIntent,
            Boolean selfHarmBehavior,
            Boolean riskToOthers,
            String protectiveFactors,
            String identifiedRiskFactors,
            String clinicalActionsTaken,
            String evaluatorObservations,
            LocalDateTime evaluatedAt,
            UUID evaluatedBy) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.dangerLevelId = dangerLevelId;
        this.suicidalIdeation = suicidalIdeation;
        this.hasSuicidePlan = hasSuicidePlan;
        this.hasSuicideIntent = hasSuicideIntent;
        this.selfHarmBehavior = selfHarmBehavior;
        this.riskToOthers = riskToOthers;
        this.protectiveFactors = protectiveFactors;
        this.identifiedRiskFactors = identifiedRiskFactors;
        this.clinicalActionsTaken = clinicalActionsTaken;
        this.evaluatorObservations = evaluatorObservations;
        this.evaluatedAt = evaluatedAt;
        this.evaluatedBy = evaluatedBy;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAppointmentId() { return appointmentId; }
    public void setAppointmentId(UUID appointmentId) { this.appointmentId = appointmentId; }
    public UUID getDangerLevelId() { return dangerLevelId; }
    public void setDangerLevelId(UUID dangerLevelId) { this.dangerLevelId = dangerLevelId; }
    public Boolean getSuicidalIdeation() { return suicidalIdeation; }
    public void setSuicidalIdeation(Boolean suicidalIdeation) { this.suicidalIdeation = suicidalIdeation; }
    public Boolean getHasSuicidePlan() { return hasSuicidePlan; }
    public void setHasSuicidePlan(Boolean hasSuicidePlan) { this.hasSuicidePlan = hasSuicidePlan; }
    public Boolean getHasSuicideIntent() { return hasSuicideIntent; }
    public void setHasSuicideIntent(Boolean hasSuicideIntent) { this.hasSuicideIntent = hasSuicideIntent; }
    public Boolean getSelfHarmBehavior() { return selfHarmBehavior; }
    public void setSelfHarmBehavior(Boolean selfHarmBehavior) { this.selfHarmBehavior = selfHarmBehavior; }
    public Boolean getRiskToOthers() { return riskToOthers; }
    public void setRiskToOthers(Boolean riskToOthers) { this.riskToOthers = riskToOthers; }
    public String getProtectiveFactors() { return protectiveFactors; }
    public void setProtectiveFactors(String protectiveFactors) { this.protectiveFactors = protectiveFactors; }
    public String getIdentifiedRiskFactors() { return identifiedRiskFactors; }
    public void setIdentifiedRiskFactors(String identifiedRiskFactors) { this.identifiedRiskFactors = identifiedRiskFactors; }
    public String getClinicalActionsTaken() { return clinicalActionsTaken; }
    public void setClinicalActionsTaken(String clinicalActionsTaken) { this.clinicalActionsTaken = clinicalActionsTaken; }
    public String getEvaluatorObservations() { return evaluatorObservations; }
    public void setEvaluatorObservations(String evaluatorObservations) { this.evaluatorObservations = evaluatorObservations; }
    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
    public void setEvaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; }
    public UUID getEvaluatedBy() { return evaluatedBy; }
    public void setEvaluatedBy(UUID evaluatedBy) { this.evaluatedBy = evaluatedBy; }
}
