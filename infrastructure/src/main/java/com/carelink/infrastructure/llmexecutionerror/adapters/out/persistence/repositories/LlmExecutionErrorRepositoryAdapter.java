package com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecutionerror.model.aggregate.LlmExecutionError;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.entity.LlmExecutionErrorJpaEntity;
import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.mappers.LlmExecutionErrorPersistenceMapper;

public class LlmExecutionErrorRepositoryAdapter implements LlmExecutionErrorRepository {
    private final LlmExecutionErrorJpaRepository jpaRepository;
    private final LlmExecutionErrorPersistenceMapper mapper;

    public LlmExecutionErrorRepositoryAdapter(LlmExecutionErrorJpaRepository jpaRepository, LlmExecutionErrorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LlmExecutionError save(LlmExecutionError aggregate) {
        LlmExecutionErrorJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LlmExecutionError> findById(LlmExecutionErrorId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<LlmExecutionError> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(LlmExecutionError aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
