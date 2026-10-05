package com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.llmexecutionmetric.adapters.out.persistence.entity.LlmExecutionMetricJpaEntity;

public interface LlmExecutionMetricJpaRepository extends JpaRepository<LlmExecutionMetricJpaEntity, UUID> {
}
