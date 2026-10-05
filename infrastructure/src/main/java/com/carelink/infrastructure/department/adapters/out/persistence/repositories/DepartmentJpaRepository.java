package com.carelink.infrastructure.department.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.department.adapters.out.persistence.entity.DepartmentJpaEntity;

public interface DepartmentJpaRepository extends JpaRepository<DepartmentJpaEntity, UUID> {
}
