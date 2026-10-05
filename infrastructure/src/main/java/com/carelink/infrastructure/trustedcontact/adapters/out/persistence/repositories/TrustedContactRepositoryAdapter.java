package com.carelink.infrastructure.trustedcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.trustedcontact.model.aggregate.TrustedContact;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.entity.TrustedContactJpaEntity;
import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.mappers.TrustedContactPersistenceMapper;

public class TrustedContactRepositoryAdapter implements TrustedContactRepository {
    private final TrustedContactJpaRepository jpaRepository;
    private final TrustedContactPersistenceMapper mapper;

    public TrustedContactRepositoryAdapter(TrustedContactJpaRepository jpaRepository, TrustedContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TrustedContact save(TrustedContact aggregate) {
        TrustedContactJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TrustedContact> findById(TrustedContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TrustedContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(TrustedContact aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
