package com.carelink.infrastructure.llmexecutionerror.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateLlmExecutionErrorRequest(
        @NotNull(message = "executionId is required")
        UUID executionId,

        @NotNull(message = "errorDetail is required")
        String errorDetail,

        @Size(max = 80, message = "errorCode must have at most 80 characters")
        String errorCode,

        @Size(max = 120, message = "vendorErrorRef must have at most 120 characters")
        String vendorErrorRef
) {
}
