package com.carelink.infrastructure.healthrecord.adapters.out.persistence.mappers;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecord.model.aggregate.HealthRecord;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.healthrecord.adapters.out.persistence.entity.HealthRecordJpaEntity;

public class HealthRecordPersistenceMapper {
    public HealthRecordJpaEntity toJpa(HealthRecord domain) {
        if (domain == null) {
            return null;
        }
        HealthRecordJpaEntity jpa = new HealthRecordJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setClientId(domain.clientId().value());
        jpa.setOpeningDate(domain.openingDate());
        jpa.setRecordCode(domain.recordCode());
        jpa.setOpenedAt(domain.openedAt());
        jpa.setClosedAt(domain.closedAt());
        jpa.setStatusId(domain.statusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy().value());
        return jpa;
    }

    public HealthRecord toDomain(HealthRecordJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return HealthRecord.restore(
                new HealthRecordId(jpa.getId()),
                new ClientId(jpa.getClientId()),
                jpa.getOpeningDate(),
                jpa.getRecordCode(),
                jpa.getOpenedAt(),
                jpa.getClosedAt(),
                new HealthRecordStatusId(jpa.getStatusId()),
                jpa.getCreatedAt(),
                new SpecialistId(jpa.getCreatedBy()));
    }
}
