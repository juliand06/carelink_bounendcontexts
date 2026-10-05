package com.carelink.infrastructure.specialistcredential.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.specialistcredential.adapters.out.persistence.entity.SpecialistCredentialJpaEntity;

public interface SpecialistCredentialJpaRepository extends JpaRepository<SpecialistCredentialJpaEntity, UUID> {
}
