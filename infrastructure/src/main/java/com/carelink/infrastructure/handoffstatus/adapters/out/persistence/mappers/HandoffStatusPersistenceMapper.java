package com.carelink.infrastructure.handoffstatus.adapters.out.persistence.mappers;

import com.carelink.domain.handoffstatus.model.aggregate.HandoffStatus;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.entity.HandoffStatusJpaEntity;

public class HandoffStatusPersistenceMapper {
    public HandoffStatusJpaEntity toJpa(HandoffStatus domain) {
        if (domain == null) {
            return null;
        }
        HandoffStatusJpaEntity jpa = new HandoffStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public HandoffStatus toDomain(HandoffStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return HandoffStatus.restore(
                new HandoffStatusId(jpa.getId()),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
