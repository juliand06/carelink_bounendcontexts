package com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.mappers;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.model.aggregate.HandoffStatusAudit;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;
import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.entity.HandoffStatusAuditJpaEntity;

public class HandoffStatusAuditPersistenceMapper {
    public HandoffStatusAuditJpaEntity toJpa(HandoffStatusAudit domain) {
        if (domain == null) {
            return null;
        }
        HandoffStatusAuditJpaEntity jpa = new HandoffStatusAuditJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setHandoffId(domain.handoffId().value());
        jpa.setHandoffStatusId(domain.handoffStatusId().value());
        jpa.setRecordedAt(domain.recordedAt());
        jpa.setTransitionedAt(domain.transitionedAt());
        return jpa;
    }

    public HandoffStatusAudit toDomain(HandoffStatusAuditJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return HandoffStatusAudit.restore(
                new HandoffStatusAuditId(jpa.getId()),
                new HandoffId(jpa.getHandoffId()),
                new HandoffStatusId(jpa.getHandoffStatusId()),
                jpa.getRecordedAt(),
                jpa.getTransitionedAt());
    }
}
