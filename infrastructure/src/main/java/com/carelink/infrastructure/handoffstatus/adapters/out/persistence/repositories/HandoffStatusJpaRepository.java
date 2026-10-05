package com.carelink.infrastructure.handoffstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.handoffstatus.adapters.out.persistence.entity.HandoffStatusJpaEntity;

public interface HandoffStatusJpaRepository extends JpaRepository<HandoffStatusJpaEntity, UUID> {
}
