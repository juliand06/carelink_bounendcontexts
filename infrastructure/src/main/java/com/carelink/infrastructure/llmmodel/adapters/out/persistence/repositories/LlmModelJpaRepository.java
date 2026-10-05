package com.carelink.infrastructure.llmmodel.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.llmmodel.adapters.out.persistence.entity.LlmModelJpaEntity;

public interface LlmModelJpaRepository extends JpaRepository<LlmModelJpaEntity, UUID> {
}
