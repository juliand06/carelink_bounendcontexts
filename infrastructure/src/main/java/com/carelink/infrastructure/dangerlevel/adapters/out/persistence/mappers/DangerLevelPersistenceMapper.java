package com.carelink.infrastructure.dangerlevel.adapters.out.persistence.mappers;

import com.carelink.domain.dangerlevel.model.aggregate.DangerLevel;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.entity.DangerLevelJpaEntity;

public class DangerLevelPersistenceMapper {
    public DangerLevelJpaEntity toJpa(DangerLevel domain) {
        if (domain == null) {
            return null;
        }
        DangerLevelJpaEntity jpa = new DangerLevelJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setSeverityRank(domain.severityRank());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public DangerLevel toDomain(DangerLevelJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return DangerLevel.restore(
                new DangerLevelId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getSeverityRank(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
