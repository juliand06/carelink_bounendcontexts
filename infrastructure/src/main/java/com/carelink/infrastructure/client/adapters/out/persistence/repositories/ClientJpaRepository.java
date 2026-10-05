package com.carelink.infrastructure.client.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.client.adapters.out.persistence.entity.ClientJpaEntity;

public interface ClientJpaRepository extends JpaRepository<ClientJpaEntity, UUID> {
}
