package com.carelink.infrastructure.llmexecution.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecution.model.aggregate.LlmExecution;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;
import com.carelink.infrastructure.llmexecution.adapters.out.persistence.entity.LlmExecutionJpaEntity;
import com.carelink.infrastructure.llmexecution.adapters.out.persistence.mappers.LlmExecutionPersistenceMapper;

public class LlmExecutionRepositoryAdapter implements LlmExecutionRepository {
    private final LlmExecutionJpaRepository jpaRepository;
    private final LlmExecutionPersistenceMapper mapper;

    public LlmExecutionRepositoryAdapter(LlmExecutionJpaRepository jpaRepository, LlmExecutionPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LlmExecution save(LlmExecution aggregate) {
        LlmExecutionJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LlmExecution> findById(LlmExecutionId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<LlmExecution> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(LlmExecution aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
