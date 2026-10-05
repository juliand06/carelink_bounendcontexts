package com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.mappers;

import com.carelink.domain.healthrecordstatus.model.aggregate.HealthRecordStatus;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.entity.HealthRecordStatusJpaEntity;

public class HealthRecordStatusPersistenceMapper {
    public HealthRecordStatusJpaEntity toJpa(HealthRecordStatus domain) {
        if (domain == null) {
            return null;
        }
        HealthRecordStatusJpaEntity jpa = new HealthRecordStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public HealthRecordStatus toDomain(HealthRecordStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return HealthRecordStatus.restore(
                new HealthRecordStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
