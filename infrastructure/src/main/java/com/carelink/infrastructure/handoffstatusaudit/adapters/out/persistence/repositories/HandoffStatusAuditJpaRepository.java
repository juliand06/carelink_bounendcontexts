package com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.handoffstatusaudit.adapters.out.persistence.entity.HandoffStatusAuditJpaEntity;

public interface HandoffStatusAuditJpaRepository extends JpaRepository<HandoffStatusAuditJpaEntity, UUID> {
}
