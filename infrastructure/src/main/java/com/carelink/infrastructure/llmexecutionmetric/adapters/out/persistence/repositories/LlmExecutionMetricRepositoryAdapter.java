package com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecutionmetric.model.aggregate.LlmExecutionMetric;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.entity.LlmExecutionMetricJpaEntity;
import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.mappers.LlmExecutionMetricPersistenceMapper;

public class LlmExecutionMetricRepositoryAdapter implements LlmExecutionMetricRepository {
    private final LlmExecutionMetricJpaRepository jpaRepository;
    private final LlmExecutionMetricPersistenceMapper mapper;

    public LlmExecutionMetricRepositoryAdapter(LlmExecutionMetricJpaRepository jpaRepository, LlmExecutionMetricPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LlmExecutionMetric save(LlmExecutionMetric aggregate) {
        LlmExecutionMetricJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LlmExecutionMetric> findById(LlmExecutionMetricId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<LlmExecutionMetric> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(LlmExecutionMetric aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
