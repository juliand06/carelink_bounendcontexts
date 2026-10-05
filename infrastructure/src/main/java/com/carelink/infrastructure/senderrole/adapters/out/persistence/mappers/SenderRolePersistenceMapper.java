package com.carelink.infrastructure.senderrole.adapters.out.persistence.mappers;

import com.carelink.domain.senderrole.model.aggregate.SenderRole;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.infrastructure.senderrole.adapters.out.persistence.entity.SenderRoleJpaEntity;

public class SenderRolePersistenceMapper {
    public SenderRoleJpaEntity toJpa(SenderRole domain) {
        if (domain == null) {
            return null;
        }
        SenderRoleJpaEntity jpa = new SenderRoleJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setLabel(domain.label());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public SenderRole toDomain(SenderRoleJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }
        return SenderRole.restore(
                new SenderRoleId(jpa.getId()),
                jpa.getLabel(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
