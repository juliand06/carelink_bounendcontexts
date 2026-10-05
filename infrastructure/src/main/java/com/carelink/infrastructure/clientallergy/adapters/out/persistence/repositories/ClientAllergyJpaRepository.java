package com.carelink.infrastructure.clientallergy.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.clientallergy.adapters.out.persistence.entity.ClientAllergyJpaEntity;

public interface ClientAllergyJpaRepository extends JpaRepository<ClientAllergyJpaEntity, UUID> {
}
