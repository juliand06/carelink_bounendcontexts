package com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mental_state_evaluations")
public class MentalStateEvaluationJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "appointment_id", nullable = false)
    private UUID appointmentId;

    @Column(name = "general_appearance", nullable = true, columnDefinition = "text")
    private String generalAppearance;

    @Column(name = "behavioral_observation", nullable = true, columnDefinition = "text")
    private String behavioralObservation;

    @Column(name = "attitude_toward_examiner", nullable = true, columnDefinition = "text")
    private String attitudeTowardExaminer;

    @Column(name = "level_of_consciousness", nullable = true, columnDefinition = "text")
    private String levelOfConsciousness;

    @Column(name = "orientation_status", nullable = true, columnDefinition = "text")
    private String orientationStatus;

    @Column(name = "attention_and_concentration", nullable = true, columnDefinition = "text")
    private String attentionAndConcentration;

    @Column(name = "memory_assessment", nullable = true, columnDefinition = "text")
    private String memoryAssessment;

    @Column(name = "speech_characteristics", nullable = true, columnDefinition = "text")
    private String speechCharacteristics;

    @Column(name = "mood_description", nullable = true, columnDefinition = "text")
    private String moodDescription;

    @Column(name = "affect_description", nullable = true, columnDefinition = "text")
    private String affectDescription;

    @Column(name = "thought_process", nullable = true, columnDefinition = "text")
    private String thoughtProcess;

    @Column(name = "thought_content", nullable = true, columnDefinition = "text")
    private String thoughtContent;

    @Column(name = "perceptual_disturbances", nullable = true, columnDefinition = "text")
    private String perceptualDisturbances;

    @Column(name = "judgment_assessment", nullable = true, columnDefinition = "text")
    private String judgmentAssessment;

    @Column(name = "insight_assessment", nullable = true, columnDefinition = "text")
    private String insightAssessment;

    @Column(name = "psychomotor_findings", nullable = true, columnDefinition = "text")
    private String psychomotorFindings;

    @Column(name = "examiner_remarks", nullable = true, columnDefinition = "text")
    private String examinerRemarks;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    public MentalStateEvaluationJpaEntity() { }

    public MentalStateEvaluationJpaEntity(
            UUID id,
            UUID appointmentId,
            String generalAppearance,
            String behavioralObservation,
            String attitudeTowardExaminer,
            String levelOfConsciousness,
            String orientationStatus,
            String attentionAndConcentration,
            String memoryAssessment,
            String speechCharacteristics,
            String moodDescription,
            String affectDescription,
            String thoughtProcess,
            String thoughtContent,
            String perceptualDisturbances,
            String judgmentAssessment,
            String insightAssessment,
            String psychomotorFindings,
            String examinerRemarks,
            LocalDateTime createdAt,
            UUID createdBy) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.generalAppearance = generalAppearance;
        this.behavioralObservation = behavioralObservation;
        this.attitudeTowardExaminer = attitudeTowardExaminer;
        this.levelOfConsciousness = levelOfConsciousness;
        this.orientationStatus = orientationStatus;
        this.attentionAndConcentration = attentionAndConcentration;
        this.memoryAssessment = memoryAssessment;
        this.speechCharacteristics = speechCharacteristics;
        this.moodDescription = moodDescription;
        this.affectDescription = affectDescription;
        this.thoughtProcess = thoughtProcess;
        this.thoughtContent = thoughtContent;
        this.perceptualDisturbances = perceptualDisturbances;
        this.judgmentAssessment = judgmentAssessment;
        this.insightAssessment = insightAssessment;
        this.psychomotorFindings = psychomotorFindings;
        this.examinerRemarks = examinerRemarks;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAppointmentId() { return appointmentId; }
    public void setAppointmentId(UUID appointmentId) { this.appointmentId = appointmentId; }
    public String getGeneralAppearance() { return generalAppearance; }
    public void setGeneralAppearance(String generalAppearance) { this.generalAppearance = generalAppearance; }
    public String getBehavioralObservation() { return behavioralObservation; }
    public void setBehavioralObservation(String behavioralObservation) { this.behavioralObservation = behavioralObservation; }
    public String getAttitudeTowardExaminer() { return attitudeTowardExaminer; }
    public void setAttitudeTowardExaminer(String attitudeTowardExaminer) { this.attitudeTowardExaminer = attitudeTowardExaminer; }
    public String getLevelOfConsciousness() { return levelOfConsciousness; }
    public void setLevelOfConsciousness(String levelOfConsciousness) { this.levelOfConsciousness = levelOfConsciousness; }
    public String getOrientationStatus() { return orientationStatus; }
    public void setOrientationStatus(String orientationStatus) { this.orientationStatus = orientationStatus; }
    public String getAttentionAndConcentration() { return attentionAndConcentration; }
    public void setAttentionAndConcentration(String attentionAndConcentration) { this.attentionAndConcentration = attentionAndConcentration; }
    public String getMemoryAssessment() { return memoryAssessment; }
    public void setMemoryAssessment(String memoryAssessment) { this.memoryAssessment = memoryAssessment; }
    public String getSpeechCharacteristics() { return speechCharacteristics; }
    public void setSpeechCharacteristics(String speechCharacteristics) { this.speechCharacteristics = speechCharacteristics; }
    public String getMoodDescription() { return moodDescription; }
    public void setMoodDescription(String moodDescription) { this.moodDescription = moodDescription; }
    public String getAffectDescription() { return affectDescription; }
    public void setAffectDescription(String affectDescription) { this.affectDescription = affectDescription; }
    public String getThoughtProcess() { return thoughtProcess; }
    public void setThoughtProcess(String thoughtProcess) { this.thoughtProcess = thoughtProcess; }
    public String getThoughtContent() { return thoughtContent; }
    public void setThoughtContent(String thoughtContent) { this.thoughtContent = thoughtContent; }
    public String getPerceptualDisturbances() { return perceptualDisturbances; }
    public void setPerceptualDisturbances(String perceptualDisturbances) { this.perceptualDisturbances = perceptualDisturbances; }
    public String getJudgmentAssessment() { return judgmentAssessment; }
    public void setJudgmentAssessment(String judgmentAssessment) { this.judgmentAssessment = judgmentAssessment; }
    public String getInsightAssessment() { return insightAssessment; }
    public void setInsightAssessment(String insightAssessment) { this.insightAssessment = insightAssessment; }
    public String getPsychomotorFindings() { return psychomotorFindings; }
    public void setPsychomotorFindings(String psychomotorFindings) { this.psychomotorFindings = psychomotorFindings; }
    public String getExaminerRemarks() { return examinerRemarks; }
    public void setExaminerRemarks(String examinerRemarks) { this.examinerRemarks = examinerRemarks; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }
}
