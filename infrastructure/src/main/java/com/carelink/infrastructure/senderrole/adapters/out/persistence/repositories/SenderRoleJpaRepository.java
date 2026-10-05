package com.carelink.infrastructure.senderrole.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.senderrole.adapters.out.persistence.entity.SenderRoleJpaEntity;

public interface SenderRoleJpaRepository extends JpaRepository<SenderRoleJpaEntity, UUID> {
}
