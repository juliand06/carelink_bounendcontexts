package com.carelink.infrastructure.urgencylevel.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.urgencylevel.adapters.out.persistence.entity.UrgencyLevelJpaEntity;

public interface UrgencyLevelJpaRepository extends JpaRepository<UrgencyLevelJpaEntity, UUID> {
}
