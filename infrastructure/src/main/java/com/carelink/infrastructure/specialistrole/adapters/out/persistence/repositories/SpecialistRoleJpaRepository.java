package com.carelink.infrastructure.specialistrole.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.specialistrole.adapters.out.persistence.entity.SpecialistRoleJpaEntity;

public interface SpecialistRoleJpaRepository extends JpaRepository<SpecialistRoleJpaEntity, UUID> {
}
