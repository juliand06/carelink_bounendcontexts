package com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.evaluationinstrumenttype.adapters.out.persistence.entity.EvaluationInstrumentTypeJpaEntity;

public interface EvaluationInstrumentTypeJpaRepository extends JpaRepository<EvaluationInstrumentTypeJpaEntity, UUID> {
}
