package com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.mappers;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.model.aggregate.LlmExecutionError;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;
import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.entity.LlmExecutionErrorJpaEntity;

public class LlmExecutionErrorPersistenceMapper {
    public LlmExecutionErrorJpaEntity toJpa(LlmExecutionError domain) {
        if (domain == null) {
            return null;
        }
        LlmExecutionErrorJpaEntity jpa = new LlmExecutionErrorJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setExecutionId(domain.executionId().value());
        jpa.setErrorDetail(domain.errorDetail());
        jpa.setErrorCode(domain.errorCode());
        jpa.setVendorErrorRef(domain.vendorErrorRef());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public LlmExecutionError toDomain(LlmExecutionErrorJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return LlmExecutionError.restore(
                new LlmExecutionErrorId(jpa.getId()),
                new LlmExecutionId(jpa.getExecutionId()),
                jpa.getErrorDetail(),
                jpa.getErrorCode(),
                jpa.getVendorErrorRef(),
                jpa.getCreatedAt());
    }
}
