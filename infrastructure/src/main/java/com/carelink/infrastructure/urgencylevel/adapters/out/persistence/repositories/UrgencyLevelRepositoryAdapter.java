package com.carelink.infrastructure.urgencylevel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.urgencylevel.model.aggregate.UrgencyLevel;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.entity.UrgencyLevelJpaEntity;
import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.mappers.UrgencyLevelPersistenceMapper;

public class UrgencyLevelRepositoryAdapter implements UrgencyLevelRepository {
    private final UrgencyLevelJpaRepository jpaRepository;
    private final UrgencyLevelPersistenceMapper mapper;

    public UrgencyLevelRepositoryAdapter(UrgencyLevelJpaRepository jpaRepository, UrgencyLevelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public UrgencyLevel save(UrgencyLevel aggregate) {
        UrgencyLevelJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<UrgencyLevel> findById(UrgencyLevelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<UrgencyLevel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(UrgencyLevel aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
