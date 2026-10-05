package com.carelink.domain.common.exception;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;

public class LlmExecutionNotFoundException extends RuntimeException {
    public LlmExecutionNotFoundException(LlmExecutionId id) {
        super("LlmExecution not found with id: " + id.value());
    }
}
