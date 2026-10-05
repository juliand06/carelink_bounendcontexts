package com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.informedconsenttype.model.aggregate.InformedConsentType;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.entity.InformedConsentTypeJpaEntity;
import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.mappers.InformedConsentTypePersistenceMapper;

public class InformedConsentTypeRepositoryAdapter implements InformedConsentTypeRepository {
    private final InformedConsentTypeJpaRepository jpaRepository;
    private final InformedConsentTypePersistenceMapper mapper;

    public InformedConsentTypeRepositoryAdapter(InformedConsentTypeJpaRepository jpaRepository, InformedConsentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public InformedConsentType save(InformedConsentType aggregate) {
        InformedConsentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<InformedConsentType> findById(InformedConsentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<InformedConsentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(InformedConsentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
