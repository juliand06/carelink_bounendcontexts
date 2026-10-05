package com.carelink.application.iddocumenttype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record IdDocumentTypeResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
