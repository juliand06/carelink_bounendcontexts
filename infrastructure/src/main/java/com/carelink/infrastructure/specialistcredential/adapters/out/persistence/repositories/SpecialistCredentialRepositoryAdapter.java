package com.carelink.infrastructure.specialistcredential.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.specialistcredential.model.aggregate.SpecialistCredential;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.entity.SpecialistCredentialJpaEntity;
import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.mappers.SpecialistCredentialPersistenceMapper;

public class SpecialistCredentialRepositoryAdapter implements SpecialistCredentialRepository {
    private final SpecialistCredentialJpaRepository jpaRepository;
    private final SpecialistCredentialPersistenceMapper mapper;

    public SpecialistCredentialRepositoryAdapter(SpecialistCredentialJpaRepository jpaRepository, SpecialistCredentialPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SpecialistCredential save(SpecialistCredential aggregate) {
        SpecialistCredentialJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SpecialistCredential> findById(SpecialistCredentialId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SpecialistCredential> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SpecialistCredential aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
