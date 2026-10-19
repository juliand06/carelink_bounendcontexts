package com.carelink.application.security.mapper;

import java.util.List;

import com.carelink.application.security.dto.RoleResponse;
import com.carelink.application.security.dto.UserResponse;
import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.aggregate.User;

public final class UserResponseMapper {
    private UserResponseMapper() {}

    public static RoleResponse toRoleResponse(Role role) {
        if (role == null) return null;
        return new RoleResponse(role.id().value(), role.name(), role.authority());
    }

    public static UserResponse toResponse(User user) {
        if (user == null) return null;
        List<RoleResponse> roles = user.roles().stream()
                .map(UserResponseMapper::toRoleResponse)
                .toList();
        return new UserResponse(
                user.id().value(),
                user.email(),
                roles,
                user.status().name()
        );
    }
}
