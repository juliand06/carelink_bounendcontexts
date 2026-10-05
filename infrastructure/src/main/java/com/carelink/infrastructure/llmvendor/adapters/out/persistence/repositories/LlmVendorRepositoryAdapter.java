package com.carelink.infrastructure.llmvendor.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.llmvendor.model.aggregate.LlmVendor;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import com.carelink.infrastructure.llmvendor.adapters.out.persistence.entity.LlmVendorJpaEntity;
import com.carelink.infrastructure.llmvendor.adapters.out.persistence.mappers.LlmVendorPersistenceMapper;

public class LlmVendorRepositoryAdapter implements LlmVendorRepository {
    private final LlmVendorJpaRepository jpaRepository;
    private final LlmVendorPersistenceMapper mapper;

    public LlmVendorRepositoryAdapter(LlmVendorJpaRepository jpaRepository, LlmVendorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LlmVendor save(LlmVendor aggregate) {
        LlmVendorJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LlmVendor> findById(LlmVendorId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<LlmVendor> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(LlmVendor aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
