package com.carelink.infrastructure.llmvendor.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.llmvendor.adapters.out.persistence.entity.LlmVendorJpaEntity;

public interface LlmVendorJpaRepository extends JpaRepository<LlmVendorJpaEntity, UUID> {
}
