package com.carelink.infrastructure.security.adapters.out.persistence.mappers;

import com.carelink.domain.security.model.aggregate.RefreshToken;
import com.carelink.domain.security.model.valueobject.RefreshTokenId;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.infrastructure.security.adapters.out.persistence.entity.RefreshTokenJpaEntity;

public class RefreshTokenPersistenceMapper {

    public RefreshTokenJpaEntity toJpa(RefreshToken domain) {
        if (domain == null) return null;
        return new RefreshTokenJpaEntity(
                domain.id().value(),
                domain.userId().value(),
                domain.token(),
                domain.expiresAt(),
                domain.isRevoked()
        );
    }

    public RefreshToken toDomain(RefreshTokenJpaEntity jpa) {
        if (jpa == null) return null;
        return RefreshToken.restore(
                new RefreshTokenId(jpa.getId()),
                new UserId(jpa.getUserId()),
                jpa.getToken(),
                jpa.getExpiresAt(),
                jpa.isRevoked()
        );
    }
}
