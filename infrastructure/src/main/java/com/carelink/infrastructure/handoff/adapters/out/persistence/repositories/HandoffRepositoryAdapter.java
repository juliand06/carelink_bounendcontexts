package com.carelink.infrastructure.handoff.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoff.model.aggregate.Handoff;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.infrastructure.handoff.adapters.out.persistence.entity.HandoffJpaEntity;
import com.carelink.infrastructure.handoff.adapters.out.persistence.mappers.HandoffPersistenceMapper;

public class HandoffRepositoryAdapter implements HandoffRepository {
    private final HandoffJpaRepository jpaRepository;
    private final HandoffPersistenceMapper mapper;

    public HandoffRepositoryAdapter(HandoffJpaRepository jpaRepository, HandoffPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Handoff save(Handoff aggregate) {
        HandoffJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Handoff> findById(HandoffId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Handoff> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Handoff aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
