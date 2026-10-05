package com.carelink.application.handoffstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HandoffStatusResponse(
        UUID id,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
