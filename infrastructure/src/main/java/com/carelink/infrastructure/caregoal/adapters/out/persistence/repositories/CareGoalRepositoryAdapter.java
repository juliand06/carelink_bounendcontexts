package com.carelink.infrastructure.caregoal.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.caregoal.model.aggregate.CareGoal;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;
import com.carelink.infrastructure.caregoal.adapters.out.persistence.entity.CareGoalJpaEntity;
import com.carelink.infrastructure.caregoal.adapters.out.persistence.mappers.CareGoalPersistenceMapper;

public class CareGoalRepositoryAdapter implements CareGoalRepository {
    private final CareGoalJpaRepository jpaRepository;
    private final CareGoalPersistenceMapper mapper;

    public CareGoalRepositoryAdapter(CareGoalJpaRepository jpaRepository, CareGoalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CareGoal save(CareGoal aggregate) {
        CareGoalJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CareGoal> findById(CareGoalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<CareGoal> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(CareGoal aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
