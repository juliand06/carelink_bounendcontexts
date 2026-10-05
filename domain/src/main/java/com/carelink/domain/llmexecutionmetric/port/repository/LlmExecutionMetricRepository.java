package com.carelink.domain.llmexecutionmetric.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecutionmetric.model.aggregate.LlmExecutionMetric;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;

public interface LlmExecutionMetricRepository {
    LlmExecutionMetric save(LlmExecutionMetric aggregate);

    Optional<LlmExecutionMetric> findById(LlmExecutionMetricId id);

    List<LlmExecutionMetric> findAll();

    void delete(LlmExecutionMetric aggregate);
}
