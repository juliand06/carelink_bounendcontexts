package com.carelink.infrastructure.appointmenttype.adapters.out.persistence.mappers;

import com.carelink.domain.appointmenttype.model.aggregate.AppointmentType;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.infrastructure.appointmenttype.adapters.out.persistence.entity.AppointmentTypeJpaEntity;

public class AppointmentTypePersistenceMapper {
    public AppointmentTypeJpaEntity toJpa(AppointmentType domain) {
        if (domain == null) {
            return null;
        }
        AppointmentTypeJpaEntity jpa = new AppointmentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public AppointmentType toDomain(AppointmentTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return AppointmentType.restore(
                new AppointmentTypeId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
