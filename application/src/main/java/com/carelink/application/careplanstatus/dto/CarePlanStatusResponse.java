package com.carelink.application.careplanstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CarePlanStatusResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
