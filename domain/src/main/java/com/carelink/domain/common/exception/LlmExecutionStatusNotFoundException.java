package com.carelink.domain.common.exception;

import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;

public class LlmExecutionStatusNotFoundException extends RuntimeException {
    public LlmExecutionStatusNotFoundException(LlmExecutionStatusId id) {
        super("LlmExecutionStatus not found with id: " + id.value());
    }
}
