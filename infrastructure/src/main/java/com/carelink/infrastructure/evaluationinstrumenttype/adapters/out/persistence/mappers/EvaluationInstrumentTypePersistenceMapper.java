package com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.mappers;

import com.carelink.domain.evaluationinstrumenttype.model.aggregate.EvaluationInstrumentType;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.entity.EvaluationInstrumentTypeJpaEntity;

public class EvaluationInstrumentTypePersistenceMapper {
    public EvaluationInstrumentTypeJpaEntity toJpa(EvaluationInstrumentType domain) {
        if (domain == null) {
            return null;
        }
        EvaluationInstrumentTypeJpaEntity jpa = new EvaluationInstrumentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setLabel(domain.label());
        jpa.setActive(domain.active());
        jpa.setDescription(domain.description());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public EvaluationInstrumentType toDomain(EvaluationInstrumentTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return EvaluationInstrumentType.restore(
                new EvaluationInstrumentTypeId(jpa.getId()),
                jpa.getCode(),
                jpa.getLabel(),
                jpa.getActive(),
                jpa.getDescription(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
