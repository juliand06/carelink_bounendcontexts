package com.carelink.infrastructure.handoffstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoffstatus.model.aggregate.HandoffStatus;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.entity.HandoffStatusJpaEntity;
import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.mappers.HandoffStatusPersistenceMapper;

public class HandoffStatusRepositoryAdapter implements HandoffStatusRepository {
    private final HandoffStatusJpaRepository jpaRepository;
    private final HandoffStatusPersistenceMapper mapper;

    public HandoffStatusRepositoryAdapter(HandoffStatusJpaRepository jpaRepository, HandoffStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public HandoffStatus save(HandoffStatus aggregate) {
        HandoffStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<HandoffStatus> findById(HandoffStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<HandoffStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(HandoffStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
