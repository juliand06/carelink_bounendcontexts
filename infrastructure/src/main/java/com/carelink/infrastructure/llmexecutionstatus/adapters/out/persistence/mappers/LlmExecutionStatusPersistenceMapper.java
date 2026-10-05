package com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.mappers;

import com.carelink.domain.llmexecutionstatus.model.aggregate.LlmExecutionStatus;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.entity.LlmExecutionStatusJpaEntity;

public class LlmExecutionStatusPersistenceMapper {
    public LlmExecutionStatusJpaEntity toJpa(LlmExecutionStatus domain) {
        if (domain == null) {
            return null;
        }
        LlmExecutionStatusJpaEntity jpa = new LlmExecutionStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public LlmExecutionStatus toDomain(LlmExecutionStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return LlmExecutionStatus.restore(
                new LlmExecutionStatusId(jpa.getId()),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
