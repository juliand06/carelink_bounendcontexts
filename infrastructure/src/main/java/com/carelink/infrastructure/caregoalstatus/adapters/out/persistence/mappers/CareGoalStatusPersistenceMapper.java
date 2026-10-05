package com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.mappers;

import com.carelink.domain.caregoalstatus.model.aggregate.CareGoalStatus;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.entity.CareGoalStatusJpaEntity;

public class CareGoalStatusPersistenceMapper {
    public CareGoalStatusJpaEntity toJpa(CareGoalStatus domain) {
        if (domain == null) {
            return null;
        }
        CareGoalStatusJpaEntity jpa = new CareGoalStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public CareGoalStatus toDomain(CareGoalStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return CareGoalStatus.restore(
                new CareGoalStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
