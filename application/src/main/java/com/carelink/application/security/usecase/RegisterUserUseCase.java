package com.carelink.application.security.usecase;

import com.carelink.application.security.dto.RegisterRequest;
import com.carelink.application.security.dto.UserResponse;
import com.carelink.application.security.mapper.UserResponseMapper;
import com.carelink.domain.security.exception.DomainSecurityException;
import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.port.repository.RoleRepository;
import com.carelink.domain.security.port.repository.UserRepository;
import com.carelink.domain.security.port.service.PasswordService;

public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordService passwordService;

    public RegisterUserUseCase(UserRepository userRepository,
                              RoleRepository roleRepository,
                              PasswordService passwordService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordService = passwordService;
    }

    public UserResponse execute(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DomainSecurityException("El usuario con email " + request.email() + " ya existe");
        }

        String hashedPassword = passwordService.hash(request.password());
        User user = User.create(request.email(), hashedPassword);

        Role userRole = roleRepository.findByAuthority("ROLE_USER")
                .or(() -> roleRepository.findByName("USER"))
                .orElseThrow(() -> new DomainSecurityException("Rol USER no encontrado"));
        user.assignRole(userRole);

        User savedUser = userRepository.save(user);
        return UserResponseMapper.toResponse(savedUser);
    }
}
