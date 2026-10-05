package com.carelink.application.healthrecordstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HealthRecordStatusResponse(
        UUID id,
        String code,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
