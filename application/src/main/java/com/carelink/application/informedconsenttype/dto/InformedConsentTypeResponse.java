package com.carelink.application.informedconsenttype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record InformedConsentTypeResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
