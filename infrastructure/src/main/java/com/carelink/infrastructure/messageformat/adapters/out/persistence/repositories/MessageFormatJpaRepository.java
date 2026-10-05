package com.carelink.infrastructure.messageformat.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.messageformat.adapters.out.persistence.entity.MessageFormatJpaEntity;

public interface MessageFormatJpaRepository extends JpaRepository<MessageFormatJpaEntity, UUID> {
}
