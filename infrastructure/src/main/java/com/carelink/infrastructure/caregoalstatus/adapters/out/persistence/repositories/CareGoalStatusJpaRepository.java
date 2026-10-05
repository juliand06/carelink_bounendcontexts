package com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.caregoalstatus.adapters.out.persistence.entity.CareGoalStatusJpaEntity;

public interface CareGoalStatusJpaRepository extends JpaRepository<CareGoalStatusJpaEntity, UUID> {
}
