package com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.mappers;

import com.carelink.domain.drugadministrationroute.model.aggregate.DrugAdministrationRoute;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;
import com.carelink.infrastructure.drugadministrationroute.adapters.out.persistence.entity.DrugAdministrationRouteJpaEntity;

public class DrugAdministrationRoutePersistenceMapper {
    public DrugAdministrationRouteJpaEntity toJpa(DrugAdministrationRoute domain) {
        if (domain == null) {
            return null;
        }
        DrugAdministrationRouteJpaEntity jpa = new DrugAdministrationRouteJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public DrugAdministrationRoute toDomain(DrugAdministrationRouteJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return DrugAdministrationRoute.restore(
                new DrugAdministrationRouteId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
