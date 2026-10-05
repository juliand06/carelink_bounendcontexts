package com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.mappers;

import com.carelink.domain.informedconsenttype.model.aggregate.InformedConsentType;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;
import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.entity.InformedConsentTypeJpaEntity;

public class InformedConsentTypePersistenceMapper {
    public InformedConsentTypeJpaEntity toJpa(InformedConsentType domain) {
        if (domain == null) {
            return null;
        }
        InformedConsentTypeJpaEntity jpa = new InformedConsentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setDescription(domain.description());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public InformedConsentType toDomain(InformedConsentTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return InformedConsentType.restore(
                new InformedConsentTypeId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getDescription(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
