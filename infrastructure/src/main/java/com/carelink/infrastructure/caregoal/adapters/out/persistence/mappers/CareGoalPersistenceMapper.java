package com.carelink.infrastructure.caregoal.adapters.out.persistence.mappers;

import com.carelink.domain.caregoal.model.aggregate.CareGoal;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.infrastructure.caregoal.adapters.out.persistence.entity.CareGoalJpaEntity;

public class CareGoalPersistenceMapper {
    public CareGoalJpaEntity toJpa(CareGoal domain) {
        if (domain == null) {
            return null;
        }
        CareGoalJpaEntity jpa = new CareGoalJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCarePlanId(domain.carePlanId().value());
        jpa.setGoalDescription(domain.goalDescription());
        jpa.setTargetDate(domain.targetDate());
        jpa.setAchievedAt(domain.achievedAt());
        jpa.setProgressNotes(domain.progressNotes());
        jpa.setCareGoalStatusId(domain.careGoalStatusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public CareGoal toDomain(CareGoalJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return CareGoal.restore(
                new CareGoalId(jpa.getId()),
                new CarePlanId(jpa.getCarePlanId()),
                jpa.getGoalDescription(),
                jpa.getTargetDate(),
                jpa.getAchievedAt(),
                jpa.getProgressNotes(),
                new CareGoalStatusId(jpa.getCareGoalStatusId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
