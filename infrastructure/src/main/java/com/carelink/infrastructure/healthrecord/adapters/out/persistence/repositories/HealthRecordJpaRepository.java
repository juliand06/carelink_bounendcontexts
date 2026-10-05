package com.carelink.infrastructure.healthrecord.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.healthrecord.adapters.out.persistence.entity.HealthRecordJpaEntity;

public interface HealthRecordJpaRepository extends JpaRepository<HealthRecordJpaEntity, UUID> {
}
