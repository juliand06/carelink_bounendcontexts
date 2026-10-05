package com.carelink.infrastructure.supportmessage.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.supportmessage.adapters.out.persistence.entity.SupportMessageJpaEntity;

public interface SupportMessageJpaRepository extends JpaRepository<SupportMessageJpaEntity, UUID> {
}
