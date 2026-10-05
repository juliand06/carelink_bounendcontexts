package com.carelink.infrastructure.dangerassessment.adapters.out.persistence.mappers;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.dangerassessment.model.aggregate.DangerAssessment;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.entity.DangerAssessmentJpaEntity;

public class DangerAssessmentPersistenceMapper {
    public DangerAssessmentJpaEntity toJpa(DangerAssessment domain) {
        if (domain == null) {
            return null;
        }
        DangerAssessmentJpaEntity jpa = new DangerAssessmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAppointmentId(domain.appointmentId().value());
        jpa.setDangerLevelId(domain.dangerLevelId().value());
        jpa.setSuicidalIdeation(domain.suicidalIdeation());
        jpa.setHasSuicidePlan(domain.hasSuicidePlan());
        jpa.setHasSuicideIntent(domain.hasSuicideIntent());
        jpa.setSelfHarmBehavior(domain.selfHarmBehavior());
        jpa.setRiskToOthers(domain.riskToOthers());
        jpa.setProtectiveFactors(domain.protectiveFactors());
        jpa.setIdentifiedRiskFactors(domain.identifiedRiskFactors());
        jpa.setClinicalActionsTaken(domain.clinicalActionsTaken());
        jpa.setEvaluatorObservations(domain.evaluatorObservations());
        jpa.setEvaluatedAt(domain.evaluatedAt());
        jpa.setEvaluatedBy(domain.evaluatedBy().value());
        return jpa;
    }

    public DangerAssessment toDomain(DangerAssessmentJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return DangerAssessment.restore(
                new DangerAssessmentId(jpa.getId()),
                new AppointmentId(jpa.getAppointmentId()),
                new DangerLevelId(jpa.getDangerLevelId()),
                jpa.getSuicidalIdeation(),
                jpa.getHasSuicidePlan(),
                jpa.getHasSuicideIntent(),
                jpa.getSelfHarmBehavior(),
                jpa.getRiskToOthers(),
                jpa.getProtectiveFactors(),
                jpa.getIdentifiedRiskFactors(),
                jpa.getClinicalActionsTaken(),
                jpa.getEvaluatorObservations(),
                jpa.getEvaluatedAt(),
                new SpecialistId(jpa.getEvaluatedBy()));
    }
}
