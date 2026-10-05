package com.carelink.infrastructure.handoff.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.handoff.adapters.out.persistence.entity.HandoffJpaEntity;

public interface HandoffJpaRepository extends JpaRepository<HandoffJpaEntity, UUID> {
}
