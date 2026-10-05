package com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.mappers;

import com.carelink.domain.appointmentmodality.model.aggregate.AppointmentModality;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.infrastructure.appointmentmodality.adapters.out.persistence.entity.AppointmentModalityJpaEntity;

public class AppointmentModalityPersistenceMapper {
    public AppointmentModalityJpaEntity toJpa(AppointmentModality domain) {
        if (domain == null) {
            return null;
        }
        AppointmentModalityJpaEntity jpa = new AppointmentModalityJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public AppointmentModality toDomain(AppointmentModalityJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return AppointmentModality.restore(
                new AppointmentModalityId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
