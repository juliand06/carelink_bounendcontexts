package com.carelink.application.security.dto;

import java.util.UUID;

public record RoleResponse(
        UUID id,
        String name,
        String authority
) {}
