package com.carelink.infrastructure.dangerlevel.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.dangerlevel.adapters.out.persistence.entity.DangerLevelJpaEntity;

public interface DangerLevelJpaRepository extends JpaRepository<DangerLevelJpaEntity, UUID> {
}
