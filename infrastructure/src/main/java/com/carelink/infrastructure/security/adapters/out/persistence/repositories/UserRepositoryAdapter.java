package com.carelink.infrastructure.security.adapters.out.persistence.repositories;

import java.util.Optional;

import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.port.repository.UserRepository;
import com.carelink.infrastructure.security.adapters.out.persistence.mappers.UserPersistenceMapper;

public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    public UserRepositoryAdapter(UserJpaRepository repository, UserPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(repository.save(mapper.toJpa(user)));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void delete(User user) {
        repository.delete(mapper.toJpa(user));
    }
}
