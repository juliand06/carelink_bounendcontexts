package com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.llmexecutionstatus.adapters.out.persistence.entity.LlmExecutionStatusJpaEntity;

public interface LlmExecutionStatusJpaRepository extends JpaRepository<LlmExecutionStatusJpaEntity, UUID> {
}
