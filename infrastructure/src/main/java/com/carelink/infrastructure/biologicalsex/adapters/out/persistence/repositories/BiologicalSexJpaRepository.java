package com.carelink.infrastructure.biologicalsex.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.biologicalsex.adapters.out.persistence.entity.BiologicalSexJpaEntity;

public interface BiologicalSexJpaRepository extends JpaRepository<BiologicalSexJpaEntity, UUID> {
}
