package com.carelink.infrastructure.specialistrole.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.specialistrole.model.aggregate.SpecialistRole;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import com.carelink.infrastructure.specialistrole.adapters.out.persistence.entity.SpecialistRoleJpaEntity;
import com.carelink.infrastructure.specialistrole.adapters.out.persistence.mappers.SpecialistRolePersistenceMapper;

public class SpecialistRoleRepositoryAdapter implements SpecialistRoleRepository {
    private final SpecialistRoleJpaRepository jpaRepository;
    private final SpecialistRolePersistenceMapper mapper;

    public SpecialistRoleRepositoryAdapter(SpecialistRoleJpaRepository jpaRepository, SpecialistRolePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SpecialistRole save(SpecialistRole aggregate) {
        SpecialistRoleJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SpecialistRole> findById(SpecialistRoleId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SpecialistRole> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SpecialistRole aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
