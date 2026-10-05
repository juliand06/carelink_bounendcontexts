package com.carelink.domain.mentalstateevaluation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationRegisteredEvent;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationUpdatedEvent;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class MentalStateEvaluation extends AggregateRoot {
    private final MentalStateEvaluationId id;
    private AppointmentId appointmentId;
    private String generalAppearance;
    private String behavioralObservation;
    private String attitudeTowardExaminer;
    private String levelOfConsciousness;
    private String orientationStatus;
    private String attentionAndConcentration;
    private String memoryAssessment;
    private String speechCharacteristics;
    private String moodDescription;
    private String affectDescription;
    private String thoughtProcess;
    private String thoughtContent;
    private String perceptualDisturbances;
    private String judgmentAssessment;
    private String insightAssessment;
    private String psychomotorFindings;
    private String examinerRemarks;
    private final LocalDateTime createdAt;
    private final SpecialistId createdBy;

    private MentalStateEvaluation(
            MentalStateEvaluationId id,
            AppointmentId appointmentId,
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
            SpecialistId createdBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
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
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
    }

    public static MentalStateEvaluation register(
            AppointmentId appointmentId,
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
            SpecialistId createdBy) {
        MentalStateEvaluationId id = MentalStateEvaluationId.generate();
        LocalDateTime now = LocalDateTime.now();
        MentalStateEvaluation aggregate = new MentalStateEvaluation(
                id,
                appointmentId,
                generalAppearance,
                behavioralObservation,
                attitudeTowardExaminer,
                levelOfConsciousness,
                orientationStatus,
                attentionAndConcentration,
                memoryAssessment,
                speechCharacteristics,
                moodDescription,
                affectDescription,
                thoughtProcess,
                thoughtContent,
                perceptualDisturbances,
                judgmentAssessment,
                insightAssessment,
                psychomotorFindings,
                examinerRemarks,
                now,
                createdBy);
        aggregate.recordEvent(new MentalStateEvaluationRegisteredEvent(id, now));
        return aggregate;
    }

    public static MentalStateEvaluation restore(
            MentalStateEvaluationId id,
            AppointmentId appointmentId,
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
            SpecialistId createdBy) {
        return new MentalStateEvaluation(
                id,
                appointmentId,
                generalAppearance,
                behavioralObservation,
                attitudeTowardExaminer,
                levelOfConsciousness,
                orientationStatus,
                attentionAndConcentration,
                memoryAssessment,
                speechCharacteristics,
                moodDescription,
                affectDescription,
                thoughtProcess,
                thoughtContent,
                perceptualDisturbances,
                judgmentAssessment,
                insightAssessment,
                psychomotorFindings,
                examinerRemarks,
                createdAt,
                createdBy);
    }

    public void update(
            AppointmentId appointmentId,
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
            String examinerRemarks) {
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
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
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new MentalStateEvaluationUpdatedEvent(
                this.id,
                this.appointmentId,
                this.generalAppearance,
                this.behavioralObservation,
                this.attitudeTowardExaminer,
                this.levelOfConsciousness,
                this.orientationStatus,
                this.attentionAndConcentration,
                this.memoryAssessment,
                this.speechCharacteristics,
                this.moodDescription,
                this.affectDescription,
                this.thoughtProcess,
                this.thoughtContent,
                this.perceptualDisturbances,
                this.judgmentAssessment,
                this.insightAssessment,
                this.psychomotorFindings,
                this.examinerRemarks,
                now));
    }

    public MentalStateEvaluationId id() {
        return id;
    }

    public AppointmentId appointmentId() {
        return appointmentId;
    }

    public String generalAppearance() {
        return generalAppearance;
    }

    public String behavioralObservation() {
        return behavioralObservation;
    }

    public String attitudeTowardExaminer() {
        return attitudeTowardExaminer;
    }

    public String levelOfConsciousness() {
        return levelOfConsciousness;
    }

    public String orientationStatus() {
        return orientationStatus;
    }

    public String attentionAndConcentration() {
        return attentionAndConcentration;
    }

    public String memoryAssessment() {
        return memoryAssessment;
    }

    public String speechCharacteristics() {
        return speechCharacteristics;
    }

    public String moodDescription() {
        return moodDescription;
    }

    public String affectDescription() {
        return affectDescription;
    }

    public String thoughtProcess() {
        return thoughtProcess;
    }

    public String thoughtContent() {
        return thoughtContent;
    }

    public String perceptualDisturbances() {
        return perceptualDisturbances;
    }

    public String judgmentAssessment() {
        return judgmentAssessment;
    }

    public String insightAssessment() {
        return insightAssessment;
    }

    public String psychomotorFindings() {
        return psychomotorFindings;
    }

    public String examinerRemarks() {
        return examinerRemarks;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public SpecialistId createdBy() {
        return createdBy;
    }
}
