package com.carelink.infrastructure.careplanstatus.adapters.out.persistence.mappers;

import com.carelink.domain.careplanstatus.model.aggregate.CarePlanStatus;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.entity.CarePlanStatusJpaEntity;

public class CarePlanStatusPersistenceMapper {
    public CarePlanStatusJpaEntity toJpa(CarePlanStatus domain) {
        if (domain == null) {
            return null;
        }
        CarePlanStatusJpaEntity jpa = new CarePlanStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public CarePlanStatus toDomain(CarePlanStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return CarePlanStatus.restore(
                new CarePlanStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
