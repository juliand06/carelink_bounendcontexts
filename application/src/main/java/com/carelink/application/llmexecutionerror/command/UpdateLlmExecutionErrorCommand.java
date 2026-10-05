package com.carelink.application.llmexecutionerror.command;

import java.util.Objects;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public record UpdateLlmExecutionErrorCommand(
        LlmExecutionErrorId id,
        LlmExecutionId executionId,
        String errorDetail,
        String errorCode,
        String vendorErrorRef
) {
    public UpdateLlmExecutionErrorCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(executionId, "executionId must not be null");
        Objects.requireNonNull(errorDetail, "errorDetail must not be null");
    }
}
