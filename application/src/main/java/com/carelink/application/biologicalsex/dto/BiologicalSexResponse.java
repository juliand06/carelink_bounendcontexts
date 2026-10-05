package com.carelink.application.biologicalsex.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BiologicalSexResponse(
        UUID id,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
