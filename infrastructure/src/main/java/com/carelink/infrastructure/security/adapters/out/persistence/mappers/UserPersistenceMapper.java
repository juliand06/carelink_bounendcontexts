package com.carelink.infrastructure.security.adapters.out.persistence.mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.model.valueobject.UserStatus;
import com.carelink.infrastructure.security.adapters.out.persistence.entity.RoleJpaEntity;
import com.carelink.infrastructure.security.adapters.out.persistence.entity.UserJpaEntity;

public class UserPersistenceMapper {
    private final RolePersistenceMapper roleMapper;

    public UserPersistenceMapper(RolePersistenceMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserJpaEntity toJpa(User domain) {
        if (domain == null) return null;
        Set<RoleJpaEntity> roleEntities = domain.roles().stream()
                .map(roleMapper::toJpa)
                .collect(Collectors.toSet());

        return new UserJpaEntity(
                domain.id().value(),
                domain.email(),
                domain.password(),
                domain.status().name(),
                domain.createdAt(),
                domain.updatedAt(),
                roleEntities
        );
    }

    public User toDomain(UserJpaEntity jpa) {
        if (jpa == null) return null;
        Set<Role> roles = jpa.getRoles() != null
                ? jpa.getRoles().stream().map(roleMapper::toDomain).collect(Collectors.toSet())
                : Collections.emptySet();

        return User.restore(
                new UserId(jpa.getId()),
                jpa.getEmail(),
                jpa.getPassword(),
                roles,
                UserStatus.valueOf(jpa.getStatus()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt()
        );
    }
}
