package com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.caregoalstatus.model.aggregate.CareGoalStatus;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.entity.CareGoalStatusJpaEntity;
import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.mappers.CareGoalStatusPersistenceMapper;

public class CareGoalStatusRepositoryAdapter implements CareGoalStatusRepository {
    private final CareGoalStatusJpaRepository jpaRepository;
    private final CareGoalStatusPersistenceMapper mapper;

    public CareGoalStatusRepositoryAdapter(CareGoalStatusJpaRepository jpaRepository, CareGoalStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CareGoalStatus save(CareGoalStatus aggregate) {
        CareGoalStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CareGoalStatus> findById(CareGoalStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<CareGoalStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(CareGoalStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
