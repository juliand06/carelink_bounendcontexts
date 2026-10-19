package com.carelink.infrastructure.security.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.security.model.aggregate.RefreshToken;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.port.repository.RefreshTokenRepository;
import com.carelink.infrastructure.security.adapters.out.persistence.mappers.RefreshTokenPersistenceMapper;

public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository repository;
    private final RefreshTokenPersistenceMapper mapper;

    public RefreshTokenRepositoryAdapter(RefreshTokenJpaRepository repository, RefreshTokenPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return mapper.toDomain(repository.save(mapper.toJpa(refreshToken)));
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(mapper::toDomain);
    }

    @Override
    public List<RefreshToken> findByUserId(UserId userId) {
        return repository.findByUserId(userId.value()).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        repository.delete(mapper.toJpa(refreshToken));
    }

    @Override
    public void deleteByUserId(UserId userId) {
        repository.deleteByUserId(userId.value());
    }
}
