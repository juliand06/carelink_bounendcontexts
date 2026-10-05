package com.carelink.infrastructure.urgencylevel.adapters.out.persistence.mappers;

import com.carelink.domain.urgencylevel.model.aggregate.UrgencyLevel;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.entity.UrgencyLevelJpaEntity;

public class UrgencyLevelPersistenceMapper {
    public UrgencyLevelJpaEntity toJpa(UrgencyLevel domain) {
        if (domain == null) {
            return null;
        }
        UrgencyLevelJpaEntity jpa = new UrgencyLevelJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public UrgencyLevel toDomain(UrgencyLevelJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return UrgencyLevel.restore(
                new UrgencyLevelId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
