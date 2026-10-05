package com.carelink.infrastructure.handoffassignment.adapters.out.persistence.mappers;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.model.aggregate.HandoffAssignment;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.entity.HandoffAssignmentJpaEntity;

public class HandoffAssignmentPersistenceMapper {
    public HandoffAssignmentJpaEntity toJpa(HandoffAssignment domain) {
        if (domain == null) {
            return null;
        }
        HandoffAssignmentJpaEntity jpa = new HandoffAssignmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setHandoffId(domain.handoffId().value());
        jpa.setSpecialistId(domain.specialistId().value());
        jpa.setAssignedAt(domain.assignedAt());
        return jpa;
    }

    public HandoffAssignment toDomain(HandoffAssignmentJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return HandoffAssignment.restore(
                new HandoffAssignmentId(jpa.getId()),
                new HandoffId(jpa.getHandoffId()),
                new SpecialistId(jpa.getSpecialistId()),
                jpa.getAssignedAt());
    }
}
