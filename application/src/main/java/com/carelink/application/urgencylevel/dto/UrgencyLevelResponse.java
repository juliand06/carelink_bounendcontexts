package com.carelink.application.urgencylevel.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UrgencyLevelResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
