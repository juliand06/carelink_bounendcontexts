package com.carelink.application.supportconversationstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportConversationStatusResponse(
        UUID id,
        String code,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
