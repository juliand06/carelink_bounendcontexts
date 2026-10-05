package com.carelink.infrastructure.trustedcontact.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.trustedcontact.adapters.out.persistence.entity.TrustedContactJpaEntity;

public interface TrustedContactJpaRepository extends JpaRepository<TrustedContactJpaEntity, UUID> {
}
