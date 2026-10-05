package com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.informedconsenttype.adapters.out.persistence.entity.InformedConsentTypeJpaEntity;

public interface InformedConsentTypeJpaRepository extends JpaRepository<InformedConsentTypeJpaEntity, UUID> {
}
