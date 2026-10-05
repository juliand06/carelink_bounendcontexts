package com.carelink.infrastructure.senderrole.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.senderrole.model.aggregate.SenderRole;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import com.carelink.infrastructure.senderrole.adapters.out.persistence.entity.SenderRoleJpaEntity;
import com.carelink.infrastructure.senderrole.adapters.out.persistence.mappers.SenderRolePersistenceMapper;

public class SenderRoleRepositoryAdapter implements SenderRoleRepository {
    private final SenderRoleJpaRepository jpaRepository;
    private final SenderRolePersistenceMapper mapper;

    public SenderRoleRepositoryAdapter(SenderRoleJpaRepository jpaRepository, SenderRolePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SenderRole save(SenderRole aggregate) {
        SenderRoleJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SenderRole> findById(SenderRoleId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SenderRole> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(SenderRole aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
