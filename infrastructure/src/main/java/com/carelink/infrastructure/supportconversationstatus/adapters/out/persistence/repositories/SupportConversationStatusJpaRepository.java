package com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.supportconversationstatus.adapters.out.persistence.entity.SupportConversationStatusJpaEntity;

public interface SupportConversationStatusJpaRepository extends JpaRepository<SupportConversationStatusJpaEntity, UUID> {
}
