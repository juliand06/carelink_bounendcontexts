package com.carelink.application.llmexecution.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LlmExecutionResponse(
        UUID id,
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID executionStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
