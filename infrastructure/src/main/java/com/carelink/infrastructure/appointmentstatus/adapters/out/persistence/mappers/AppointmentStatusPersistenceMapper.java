package com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.mappers;

import com.carelink.domain.appointmentstatus.model.aggregate.AppointmentStatus;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.infrastructure.appointmentstatus.adapters.out.persistence.entity.AppointmentStatusJpaEntity;

public class AppointmentStatusPersistenceMapper {
    public AppointmentStatusJpaEntity toJpa(AppointmentStatus domain) {
        if (domain == null) {
            return null;
        }
        AppointmentStatusJpaEntity jpa = new AppointmentStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public AppointmentStatus toDomain(AppointmentStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return AppointmentStatus.restore(
                new AppointmentStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
