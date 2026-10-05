package com.carelink.infrastructure.classificationsystem.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.classificationsystem.adapters.out.persistence.entity.ClassificationSystemJpaEntity;

public interface ClassificationSystemJpaRepository extends JpaRepository<ClassificationSystemJpaEntity, UUID> {
}
