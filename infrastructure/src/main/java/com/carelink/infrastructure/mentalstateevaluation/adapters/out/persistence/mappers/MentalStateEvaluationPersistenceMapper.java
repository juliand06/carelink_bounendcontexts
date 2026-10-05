package com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.mappers;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.mentalstateevaluation.model.aggregate.MentalStateEvaluation;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.entity.MentalStateEvaluationJpaEntity;

public class MentalStateEvaluationPersistenceMapper {
    public MentalStateEvaluationJpaEntity toJpa(MentalStateEvaluation domain) {
        if (domain == null) {
            return null;
        }
        MentalStateEvaluationJpaEntity jpa = new MentalStateEvaluationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAppointmentId(domain.appointmentId().value());
        jpa.setGeneralAppearance(domain.generalAppearance());
        jpa.setBehavioralObservation(domain.behavioralObservation());
        jpa.setAttitudeTowardExaminer(domain.attitudeTowardExaminer());
        jpa.setLevelOfConsciousness(domain.levelOfConsciousness());
        jpa.setOrientationStatus(domain.orientationStatus());
        jpa.setAttentionAndConcentration(domain.attentionAndConcentration());
        jpa.setMemoryAssessment(domain.memoryAssessment());
        jpa.setSpeechCharacteristics(domain.speechCharacteristics());
        jpa.setMoodDescription(domain.moodDescription());
        jpa.setAffectDescription(domain.affectDescription());
        jpa.setThoughtProcess(domain.thoughtProcess());
        jpa.setThoughtContent(domain.thoughtContent());
        jpa.setPerceptualDisturbances(domain.perceptualDisturbances());
        jpa.setJudgmentAssessment(domain.judgmentAssessment());
        jpa.setInsightAssessment(domain.insightAssessment());
        jpa.setPsychomotorFindings(domain.psychomotorFindings());
        jpa.setExaminerRemarks(domain.examinerRemarks());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy().value());
        return jpa;
    }

    public MentalStateEvaluation toDomain(MentalStateEvaluationJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return MentalStateEvaluation.restore(
                new MentalStateEvaluationId(jpa.getId()),
                new AppointmentId(jpa.getAppointmentId()),
                jpa.getGeneralAppearance(),
                jpa.getBehavioralObservation(),
                jpa.getAttitudeTowardExaminer(),
                jpa.getLevelOfConsciousness(),
                jpa.getOrientationStatus(),
                jpa.getAttentionAndConcentration(),
                jpa.getMemoryAssessment(),
                jpa.getSpeechCharacteristics(),
                jpa.getMoodDescription(),
                jpa.getAffectDescription(),
                jpa.getThoughtProcess(),
                jpa.getThoughtContent(),
                jpa.getPerceptualDisturbances(),
                jpa.getJudgmentAssessment(),
                jpa.getInsightAssessment(),
                jpa.getPsychomotorFindings(),
                jpa.getExaminerRemarks(),
                jpa.getCreatedAt(),
                new SpecialistId(jpa.getCreatedBy()));
    }
}
