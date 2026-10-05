package com.carelink.application.llmexecutionerror.command;

import java.util.Objects;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;

public record RegisterLlmExecutionErrorCommand(
        LlmExecutionId executionId,
        String errorDetail,
        String errorCode,
        String vendorErrorRef
) {
    public RegisterLlmExecutionErrorCommand {
        Objects.requireNonNull(executionId, "executionId must not be null");
        Objects.requireNonNull(errorDetail, "errorDetail must not be null");
    }
}
