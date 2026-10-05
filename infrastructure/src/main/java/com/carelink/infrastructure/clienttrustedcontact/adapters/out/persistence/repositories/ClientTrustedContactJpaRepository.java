package com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.clienttrustedcontact.adapters.out.persistence.entity.ClientTrustedContactJpaEntity;

public interface ClientTrustedContactJpaRepository extends JpaRepository<ClientTrustedContactJpaEntity, UUID> {
}
