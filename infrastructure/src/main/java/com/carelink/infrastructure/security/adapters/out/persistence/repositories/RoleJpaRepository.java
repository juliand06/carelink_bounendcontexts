package com.carelink.infrastructure.security.adapters.out.persistence.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carelink.infrastructure.security.adapters.out.persistence.entity.RoleJpaEntity;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, UUID> {
    Optional<RoleJpaEntity> findByName(String name);
    Optional<RoleJpaEntity> findByAuthority(String authority);
    boolean existsByAuthority(String authority);
}
