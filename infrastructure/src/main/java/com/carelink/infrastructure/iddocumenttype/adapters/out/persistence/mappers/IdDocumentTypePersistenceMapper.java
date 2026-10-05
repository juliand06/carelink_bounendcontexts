package com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.mappers;

import com.carelink.domain.iddocumenttype.model.aggregate.IdDocumentType;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.infrastructure.iddocumenttype.adapters.out.persistence.entity.IdDocumentTypeJpaEntity;

public class IdDocumentTypePersistenceMapper {
    public IdDocumentTypeJpaEntity toJpa(IdDocumentType domain) {
        if (domain == null) {
            return null;
        }
        IdDocumentTypeJpaEntity jpa = new IdDocumentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public IdDocumentType toDomain(IdDocumentTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return IdDocumentType.restore(
                new IdDocumentTypeId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
