package com.carelink.application.security.usecase;

import java.util.UUID;

import com.carelink.application.security.dto.ChangePasswordRequest;
import com.carelink.domain.security.exception.InvalidCredentialsException;
import com.carelink.domain.security.exception.UserNotFoundException;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.port.repository.UserRepository;
import com.carelink.domain.security.port.service.PasswordService;

public class ChangePasswordUseCase {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public ChangePasswordUseCase(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public void execute(UUID userId, ChangePasswordRequest request) {
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (!passwordService.matches(request.currentPassword(), user.password())) {
            throw new InvalidCredentialsException("Contraseña actual incorrecta");
        }

        String newHashed = passwordService.hash(request.newPassword());
        user.changePassword(newHashed);
        userRepository.save(user);
    }
}
