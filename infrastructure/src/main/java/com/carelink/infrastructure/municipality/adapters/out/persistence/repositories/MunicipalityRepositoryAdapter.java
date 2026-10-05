package com.carelink.infrastructure.municipality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.municipality.model.aggregate.Municipality;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import com.carelink.infrastructure.municipality.adapters.out.persistence.entity.MunicipalityJpaEntity;
import com.carelink.infrastructure.municipality.adapters.out.persistence.mappers.MunicipalityPersistenceMapper;

public class MunicipalityRepositoryAdapter implements MunicipalityRepository {
    private final MunicipalityJpaRepository jpaRepository;
    private final MunicipalityPersistenceMapper mapper;

    public MunicipalityRepositoryAdapter(MunicipalityJpaRepository jpaRepository, MunicipalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Municipality save(Municipality aggregate) {
        MunicipalityJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Municipality> findById(MunicipalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Municipality> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Municipality aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
