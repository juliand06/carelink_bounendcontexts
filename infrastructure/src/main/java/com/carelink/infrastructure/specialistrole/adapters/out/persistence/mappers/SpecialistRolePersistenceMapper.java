package com.carelink.infrastructure.specialistrole.adapters.out.persistence.mappers;

import com.carelink.domain.specialistrole.model.aggregate.SpecialistRole;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.infrastructure.specialistrole.adapters.out.persistence.entity.SpecialistRoleJpaEntity;

public class SpecialistRolePersistenceMapper {
    public SpecialistRoleJpaEntity toJpa(SpecialistRole domain) {
        if (domain == null) {
            return null;
        }
        SpecialistRoleJpaEntity jpa = new SpecialistRoleJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setTitle(domain.title());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public SpecialistRole toDomain(SpecialistRoleJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SpecialistRole.restore(
                new SpecialistRoleId(jpa.getId()),
                jpa.getTitle(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
