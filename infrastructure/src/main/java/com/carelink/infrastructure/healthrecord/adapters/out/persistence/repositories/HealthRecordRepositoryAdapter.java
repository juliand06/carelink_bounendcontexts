package com.carelink.infrastructure.healthrecord.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.healthrecord.model.aggregate.HealthRecord;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.infrastructure.healthrecord.adapters.out.persistence.entity.HealthRecordJpaEntity;
import com.carelink.infrastructure.healthrecord.adapters.out.persistence.mappers.HealthRecordPersistenceMapper;

public class HealthRecordRepositoryAdapter implements HealthRecordRepository {
    private final HealthRecordJpaRepository jpaRepository;
    private final HealthRecordPersistenceMapper mapper;

    public HealthRecordRepositoryAdapter(HealthRecordJpaRepository jpaRepository, HealthRecordPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public HealthRecord save(HealthRecord aggregate) {
        HealthRecordJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<HealthRecord> findById(HealthRecordId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<HealthRecord> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(HealthRecord aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
