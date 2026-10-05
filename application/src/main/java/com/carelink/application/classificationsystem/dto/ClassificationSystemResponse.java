package com.carelink.application.classificationsystem.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClassificationSystemResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        String edition,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
