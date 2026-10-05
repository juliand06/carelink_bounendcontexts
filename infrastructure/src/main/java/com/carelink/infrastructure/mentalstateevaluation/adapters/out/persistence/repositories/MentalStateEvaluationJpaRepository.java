package com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.mentalstateevaluation.adapters.out.persistence.entity.MentalStateEvaluationJpaEntity;

public interface MentalStateEvaluationJpaRepository extends JpaRepository<MentalStateEvaluationJpaEntity, UUID> {
}
