package com.carelink.infrastructure.llmexecution.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.llmexecution.adapters.out.persistence.entity.LlmExecutionJpaEntity;

public interface LlmExecutionJpaRepository extends JpaRepository<LlmExecutionJpaEntity, UUID> {
}
