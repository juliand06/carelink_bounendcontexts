package com.carelink.application.caregoalstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CareGoalStatusResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
