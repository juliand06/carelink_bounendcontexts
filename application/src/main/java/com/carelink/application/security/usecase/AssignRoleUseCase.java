package com.carelink.application.security.usecase;

import java.util.UUID;

import com.carelink.application.security.dto.AssignRoleRequest;
import com.carelink.application.security.dto.UserResponse;
import com.carelink.application.security.mapper.UserResponseMapper;
import com.carelink.domain.security.exception.DomainSecurityException;
import com.carelink.domain.security.exception.UserNotFoundException;
import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.port.repository.RoleRepository;
import com.carelink.domain.security.port.repository.UserRepository;

public class AssignRoleUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AssignRoleUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse execute(UUID userId, AssignRoleRequest request) {
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        String roleName = request.roleName().toUpperCase().trim();
        String authority = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;

        Role role = roleRepository.findByAuthority(authority)
                .or(() -> roleRepository.findByName(roleName.replace("ROLE_", "")))
                .orElseThrow(() -> new DomainSecurityException("Rol " + request.roleName() + " no encontrado"));

        user.assignRole(role);
        User saved = userRepository.save(user);
        return UserResponseMapper.toResponse(saved);
    }
}
