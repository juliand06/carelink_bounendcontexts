package com.carelink.infrastructure.biologicalsex.adapters.out.persistence.mappers;

import com.carelink.domain.biologicalsex.model.aggregate.BiologicalSex;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.entity.BiologicalSexJpaEntity;

public class BiologicalSexPersistenceMapper {
    public BiologicalSexJpaEntity toJpa(BiologicalSex domain) {
        if (domain == null) {
            return null;
        }
        BiologicalSexJpaEntity jpa = new BiologicalSexJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public BiologicalSex toDomain(BiologicalSexJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return BiologicalSex.restore(
                new BiologicalSexId(jpa.getId()),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
