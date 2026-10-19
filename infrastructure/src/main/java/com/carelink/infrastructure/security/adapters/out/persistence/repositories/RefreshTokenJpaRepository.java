package com.carelink.infrastructure.security.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carelink.infrastructure.security.adapters.out.persistence.entity.RefreshTokenJpaEntity;

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity, UUID> {
    Optional<RefreshTokenJpaEntity> findByToken(String token);
    List<RefreshTokenJpaEntity> findByUserId(UUID userId);

    @Modifying
    @Transactional
    void deleteByUserId(UUID userId);
}
