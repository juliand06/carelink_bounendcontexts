package com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.conversationaiconfig.adapters.out.persistence.entity.ConversationAiConfigJpaEntity;

public interface ConversationAiConfigJpaRepository extends JpaRepository<ConversationAiConfigJpaEntity, UUID> {
}
