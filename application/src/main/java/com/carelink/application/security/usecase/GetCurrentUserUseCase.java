package com.carelink.application.security.usecase;

import java.util.UUID;

import com.carelink.application.security.dto.UserResponse;
import com.carelink.application.security.mapper.UserResponseMapper;
import com.carelink.domain.security.exception.UserNotFoundException;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.model.valueobject.UserId;
import com.carelink.domain.security.port.repository.UserRepository;

public class GetCurrentUserUseCase {
    private final UserRepository userRepository;

    public GetCurrentUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse execute(UUID userId) {
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        return UserResponseMapper.toResponse(user);
    }
}
