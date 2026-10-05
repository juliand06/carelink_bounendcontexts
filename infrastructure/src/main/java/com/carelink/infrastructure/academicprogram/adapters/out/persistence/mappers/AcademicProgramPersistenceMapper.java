package com.carelink.infrastructure.academicprogram.adapters.out.persistence.mappers;

import com.carelink.domain.academicprogram.model.aggregate.AcademicProgram;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.infrastructure.academicprogram.adapters.out.persistence.entity.AcademicProgramJpaEntity;

public class AcademicProgramPersistenceMapper {
    public AcademicProgramJpaEntity toJpa(AcademicProgram domain) {
        if (domain == null) {
            return null;
        }
        AcademicProgramJpaEntity jpa = new AcademicProgramJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setTitle(domain.title());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public AcademicProgram toDomain(AcademicProgramJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return AcademicProgram.restore(
                new AcademicProgramId(jpa.getId()),
                jpa.getTitle(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
