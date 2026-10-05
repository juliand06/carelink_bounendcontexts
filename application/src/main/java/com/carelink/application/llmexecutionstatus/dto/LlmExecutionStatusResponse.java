package com.carelink.application.llmexecutionstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LlmExecutionStatusResponse(
        UUID id,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
