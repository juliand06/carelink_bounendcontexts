package com.carelink.infrastructure.specialist.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.specialist.model.aggregate.Specialist;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.infrastructure.specialist.adapters.out.persistence.entity.SpecialistJpaEntity;
import com.carelink.infrastructure.specialist.adapters.out.persistence.mappers.SpecialistPersistenceMapper;

public class SpecialistRepositoryAdapter implements SpecialistRepository {
    private final SpecialistJpaRepository jpaRepository;
    private final SpecialistPersistenceMapper mapper;

    public SpecialistRepositoryAdapter(SpecialistJpaRepository jpaRepository, SpecialistPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Specialist save(Specialist aggregate) {
        SpecialistJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Specialist> findById(SpecialistId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Specialist> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Specialist aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
