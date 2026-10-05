package com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmexecutionstatus.model.aggregate.LlmExecutionStatus;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.entity.LlmExecutionStatusJpaEntity;
import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.mappers.LlmExecutionStatusPersistenceMapper;

public class LlmExecutionStatusRepositoryAdapter implements LlmExecutionStatusRepository {
    private final LlmExecutionStatusJpaRepository jpaRepository;
    private final LlmExecutionStatusPersistenceMapper mapper;

    public LlmExecutionStatusRepositoryAdapter(LlmExecutionStatusJpaRepository jpaRepository, LlmExecutionStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LlmExecutionStatus save(LlmExecutionStatus aggregate) {
        LlmExecutionStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LlmExecutionStatus> findById(LlmExecutionStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<LlmExecutionStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(LlmExecutionStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
