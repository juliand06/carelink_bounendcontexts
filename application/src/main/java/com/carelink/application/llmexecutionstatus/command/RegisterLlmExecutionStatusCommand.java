package com.carelink.application.llmexecutionstatus.command;

import java.util.Objects;

public record RegisterLlmExecutionStatusCommand(
        String label
) {
    public RegisterLlmExecutionStatusCommand {
        Objects.requireNonNull(label, "label must not be null");
    }
}
