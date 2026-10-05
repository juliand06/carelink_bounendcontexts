package com.carelink.infrastructure.dangerassessment.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.dangerassessment.adapters.out.persistence.entity.DangerAssessmentJpaEntity;

public interface DangerAssessmentJpaRepository extends JpaRepository<DangerAssessmentJpaEntity, UUID> {
}
