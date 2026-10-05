package com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.mappers;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.model.aggregate.LlmExecutionMetric;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;
import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.entity.LlmExecutionMetricJpaEntity;

public class LlmExecutionMetricPersistenceMapper {
    public LlmExecutionMetricJpaEntity toJpa(LlmExecutionMetric domain) {
        if (domain == null) {
            return null;
        }
        LlmExecutionMetricJpaEntity jpa = new LlmExecutionMetricJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setExecutionId(domain.executionId().value());
        jpa.setInputTokens(domain.inputTokens());
        jpa.setOutputTokens(domain.outputTokens());
        jpa.setTotalTokens(domain.totalTokens());
        jpa.setTotalCost(domain.totalCost());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public LlmExecutionMetric toDomain(LlmExecutionMetricJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return LlmExecutionMetric.restore(
                new LlmExecutionMetricId(jpa.getId()),
                new LlmExecutionId(jpa.getExecutionId()),
                jpa.getInputTokens(),
                jpa.getOutputTokens(),
                jpa.getTotalTokens(),
                jpa.getTotalCost(),
                jpa.getCreatedAt());
    }
}
