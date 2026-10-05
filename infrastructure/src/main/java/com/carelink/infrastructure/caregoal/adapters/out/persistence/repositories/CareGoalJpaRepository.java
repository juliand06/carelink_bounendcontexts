package com.carelink.infrastructure.caregoal.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.caregoal.adapters.out.persistence.entity.CareGoalJpaEntity;

public interface CareGoalJpaRepository extends JpaRepository<CareGoalJpaEntity, UUID> {
}
