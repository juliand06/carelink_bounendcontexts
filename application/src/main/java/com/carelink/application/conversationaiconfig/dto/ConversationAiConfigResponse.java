package com.carelink.application.conversationaiconfig.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversationAiConfigResponse(
        UUID id,
        UUID conversationId,
        Boolean aiEnabled,
        UUID preferredModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
