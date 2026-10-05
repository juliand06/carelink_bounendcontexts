package com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoffstatusaudit.model.aggregate.HandoffStatusAudit;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.entity.HandoffStatusAuditJpaEntity;
import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.mappers.HandoffStatusAuditPersistenceMapper;

public class HandoffStatusAuditRepositoryAdapter implements HandoffStatusAuditRepository {
    private final HandoffStatusAuditJpaRepository jpaRepository;
    private final HandoffStatusAuditPersistenceMapper mapper;

    public HandoffStatusAuditRepositoryAdapter(HandoffStatusAuditJpaRepository jpaRepository, HandoffStatusAuditPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public HandoffStatusAudit save(HandoffStatusAudit aggregate) {
        HandoffStatusAuditJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<HandoffStatusAudit> findById(HandoffStatusAuditId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<HandoffStatusAudit> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(HandoffStatusAudit aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
