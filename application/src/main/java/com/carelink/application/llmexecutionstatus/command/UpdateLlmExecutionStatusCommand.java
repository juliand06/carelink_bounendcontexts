package com.carelink.application.llmexecutionstatus.command;

import java.util.Objects;

import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public record UpdateLlmExecutionStatusCommand(
        LlmExecutionStatusId id,
        String label
) {
    public UpdateLlmExecutionStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
