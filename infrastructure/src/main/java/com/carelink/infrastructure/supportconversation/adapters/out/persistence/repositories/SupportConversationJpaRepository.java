package com.carelink.infrastructure.supportconversation.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.supportconversation.adapters.out.persistence.entity.SupportConversationJpaEntity;

public interface SupportConversationJpaRepository extends JpaRepository<SupportConversationJpaEntity, UUID> {
}
