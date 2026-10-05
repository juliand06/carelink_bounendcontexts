package com.carelink.infrastructure.handoffassignment.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.handoffassignment.adapters.out.persistence.entity.HandoffAssignmentJpaEntity;

public interface HandoffAssignmentJpaRepository extends JpaRepository<HandoffAssignmentJpaEntity, UUID> {
}
