package com.carelink.infrastructure.llmmodel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmmodel.model.aggregate.LlmModel;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.infrastructure.llmmodel.adapters.out.persistence.entity.LlmModelJpaEntity;
import com.carelink.infrastructure.llmmodel.adapters.out.persistence.mappers.LlmModelPersistenceMapper;

public class LlmModelRepositoryAdapter implements LlmModelRepository {
    private final LlmModelJpaRepository jpaRepository;
    private final LlmModelPersistenceMapper mapper;

    public LlmModelRepositoryAdapter(LlmModelJpaRepository jpaRepository, LlmModelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LlmModel save(LlmModel aggregate) {
        LlmModelJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LlmModel> findById(LlmModelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<LlmModel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(LlmModel aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
