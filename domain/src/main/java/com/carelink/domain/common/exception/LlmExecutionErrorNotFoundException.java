package com.carelink.domain.common.exception;

import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;

public class LlmExecutionErrorNotFoundException extends RuntimeException {
    public LlmExecutionErrorNotFoundException(LlmExecutionErrorId id) {
        super("LlmExecutionError not found with id: " + id.value());
    }
}
