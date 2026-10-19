package com.carelink.application.security.dto;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        List<RoleResponse> roles,
        String status
) {}
