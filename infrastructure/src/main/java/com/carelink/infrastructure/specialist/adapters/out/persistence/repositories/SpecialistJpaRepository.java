package com.carelink.infrastructure.specialist.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.specialist.adapters.out.persistence.entity.SpecialistJpaEntity;

public interface SpecialistJpaRepository extends JpaRepository<SpecialistJpaEntity, UUID> {
}
