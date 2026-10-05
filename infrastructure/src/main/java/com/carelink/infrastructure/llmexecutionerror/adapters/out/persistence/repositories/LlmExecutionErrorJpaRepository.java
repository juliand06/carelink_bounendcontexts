package com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.llmexecutionerror.adapters.out.persistence.entity.LlmExecutionErrorJpaEntity;

public interface LlmExecutionErrorJpaRepository extends JpaRepository<LlmExecutionErrorJpaEntity, UUID> {
}
