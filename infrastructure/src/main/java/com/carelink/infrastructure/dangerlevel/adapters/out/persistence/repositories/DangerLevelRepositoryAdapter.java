package com.carelink.infrastructure.dangerlevel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.dangerlevel.model.aggregate.DangerLevel;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.entity.DangerLevelJpaEntity;
import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.mappers.DangerLevelPersistenceMapper;

public class DangerLevelRepositoryAdapter implements DangerLevelRepository {
    private final DangerLevelJpaRepository jpaRepository;
    private final DangerLevelPersistenceMapper mapper;

    public DangerLevelRepositoryAdapter(DangerLevelJpaRepository jpaRepository, DangerLevelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DangerLevel save(DangerLevel aggregate) {
        DangerLevelJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DangerLevel> findById(DangerLevelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DangerLevel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(DangerLevel aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
