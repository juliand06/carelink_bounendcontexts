package com.carelink.application.evaluationinstrumenttype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EvaluationInstrumentTypeResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
