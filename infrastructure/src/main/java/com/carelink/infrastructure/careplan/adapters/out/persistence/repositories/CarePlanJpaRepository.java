package com.carelink.infrastructure.careplan.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.careplan.adapters.out.persistence.entity.CarePlanJpaEntity;

public interface CarePlanJpaRepository extends JpaRepository<CarePlanJpaEntity, UUID> {
}
