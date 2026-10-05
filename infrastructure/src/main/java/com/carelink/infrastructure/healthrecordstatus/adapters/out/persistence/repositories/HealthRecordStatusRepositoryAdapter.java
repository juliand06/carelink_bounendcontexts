package com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.healthrecordstatus.model.aggregate.HealthRecordStatus;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.entity.HealthRecordStatusJpaEntity;
import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.mappers.HealthRecordStatusPersistenceMapper;

public class HealthRecordStatusRepositoryAdapter implements HealthRecordStatusRepository {
    private final HealthRecordStatusJpaRepository jpaRepository;
    private final HealthRecordStatusPersistenceMapper mapper;

    public HealthRecordStatusRepositoryAdapter(HealthRecordStatusJpaRepository jpaRepository, HealthRecordStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public HealthRecordStatus save(HealthRecordStatus aggregate) {
        HealthRecordStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<HealthRecordStatus> findById(HealthRecordStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<HealthRecordStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(HealthRecordStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
