package com.carelink.application.dangerlevel.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DangerLevelResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        Integer severityRank,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
