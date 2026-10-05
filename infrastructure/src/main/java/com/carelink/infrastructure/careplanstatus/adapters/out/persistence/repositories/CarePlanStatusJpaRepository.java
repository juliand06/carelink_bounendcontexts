package com.carelink.infrastructure.careplanstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.careplanstatus.adapters.out.persistence.entity.CarePlanStatusJpaEntity;

public interface CarePlanStatusJpaRepository extends JpaRepository<CarePlanStatusJpaEntity, UUID> {
}
