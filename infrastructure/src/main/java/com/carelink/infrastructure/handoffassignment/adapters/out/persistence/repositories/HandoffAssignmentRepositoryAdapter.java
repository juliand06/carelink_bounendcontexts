package com.carelink.infrastructure.handoffassignment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoffassignment.model.aggregate.HandoffAssignment;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.entity.HandoffAssignmentJpaEntity;
import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.mappers.HandoffAssignmentPersistenceMapper;

public class HandoffAssignmentRepositoryAdapter implements HandoffAssignmentRepository {
    private final HandoffAssignmentJpaRepository jpaRepository;
    private final HandoffAssignmentPersistenceMapper mapper;

    public HandoffAssignmentRepositoryAdapter(HandoffAssignmentJpaRepository jpaRepository, HandoffAssignmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public HandoffAssignment save(HandoffAssignment aggregate) {
        HandoffAssignmentJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<HandoffAssignment> findById(HandoffAssignmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<HandoffAssignment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(HandoffAssignment aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
