package com.carelink.application.messageformat.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageFormatResponse(
        UUID id,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
