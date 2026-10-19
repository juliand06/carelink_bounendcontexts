package com.carelink.infrastructure.security.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.valueobject.RoleId;
import com.carelink.domain.security.port.repository.RoleRepository;
import com.carelink.infrastructure.security.adapters.out.persistence.mappers.RolePersistenceMapper;

public class RoleRepositoryAdapter implements RoleRepository {
    private final RoleJpaRepository repository;
    private final RolePersistenceMapper mapper;

    public RoleRepositoryAdapter(RoleJpaRepository repository, RolePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Role save(Role role) {
        return mapper.toDomain(repository.save(mapper.toJpa(role)));
    }

    @Override
    public Optional<Role> findById(RoleId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        return repository.findByAuthority(authority).map(mapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByAuthority(String authority) {
        return repository.existsByAuthority(authority);
    }
}
