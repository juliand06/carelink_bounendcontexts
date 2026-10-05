package com.carelink.infrastructure.academicprogram.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carelink.infrastructure.academicprogram.adapters.out.persistence.entity.AcademicProgramJpaEntity;

public interface AcademicProgramJpaRepository extends JpaRepository<AcademicProgramJpaEntity, UUID> {
}
