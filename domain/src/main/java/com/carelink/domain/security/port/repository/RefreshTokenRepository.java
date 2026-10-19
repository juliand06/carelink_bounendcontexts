package com.carelink.domain.security.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.security.model.aggregate.RefreshToken;
import com.carelink.domain.security.model.valueobject.UserId;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findByUserId(UserId userId);
    void delete(RefreshToken refreshToken);
    void deleteByUserId(UserId userId);
}
