package com.carelink.infrastructure.classificationsystem.adapters.out.persistence.mappers;

import com.carelink.domain.classificationsystem.model.aggregate.ClassificationSystem;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.entity.ClassificationSystemJpaEntity;

public class ClassificationSystemPersistenceMapper {
    public ClassificationSystemJpaEntity toJpa(ClassificationSystem domain) {
        if (domain == null) {
            return null;
        }
        ClassificationSystemJpaEntity jpa = new ClassificationSystemJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setEdition(domain.edition());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ClassificationSystem toDomain(ClassificationSystemJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return ClassificationSystem.restore(
                new ClassificationSystemId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getEdition(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
