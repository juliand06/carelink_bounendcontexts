package com.carelink.infrastructure.security.adapters.out.persistence.mappers;

import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.valueobject.RoleId;
import com.carelink.infrastructure.security.adapters.out.persistence.entity.RoleJpaEntity;

public class RolePersistenceMapper {

    public RoleJpaEntity toJpa(Role domain) {
        if (domain == null) return null;
        return new RoleJpaEntity(
                domain.id().value(),
                domain.name(),
                domain.authority()
        );
    }

    public Role toDomain(RoleJpaEntity jpa) {
        if (jpa == null) return null;
        return Role.restore(
                new RoleId(jpa.getId()),
                jpa.getName(),
                jpa.getAuthority()
        );
    }
}
