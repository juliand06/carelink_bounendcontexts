package com.carelink.infrastructure.conversationmember.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.conversationmember.adapters.out.persistence.entity.ConversationMemberJpaEntity;

public interface ConversationMemberJpaRepository extends JpaRepository<ConversationMemberJpaEntity, UUID> {
}
