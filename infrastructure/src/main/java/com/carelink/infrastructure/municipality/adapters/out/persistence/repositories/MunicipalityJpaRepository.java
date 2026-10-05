package com.carelink.infrastructure.municipality.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.municipality.adapters.out.persistence.entity.MunicipalityJpaEntity;

public interface MunicipalityJpaRepository extends JpaRepository<MunicipalityJpaEntity, UUID> {
}
