package com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.healthrecordstatus.adapters.out.persistence.entity.HealthRecordStatusJpaEntity;

public interface HealthRecordStatusJpaRepository extends JpaRepository<HealthRecordStatusJpaEntity, UUID> {
}
