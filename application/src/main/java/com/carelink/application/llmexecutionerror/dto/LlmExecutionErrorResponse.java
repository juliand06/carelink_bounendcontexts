package com.carelink.application.llmexecutionerror.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LlmExecutionErrorResponse(
        UUID id,
        UUID executionId,
        String errorDetail,
        String errorCode,
        String vendorErrorRef,
        LocalDateTime createdAt
) {
}
