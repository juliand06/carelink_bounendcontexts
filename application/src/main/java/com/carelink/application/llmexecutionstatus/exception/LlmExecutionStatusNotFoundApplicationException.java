package com.carelink.application.llmexecutionstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class LlmExecutionStatusNotFoundApplicationException extends NotFoundApplicationException {
    public LlmExecutionStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
