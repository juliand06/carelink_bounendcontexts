package com.carelink.application.specialistrole.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SpecialistRoleResponse(
        UUID id,
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
