package com.carelink.application.llmexecution.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class LlmExecutionNotFoundApplicationException extends NotFoundApplicationException {
    public LlmExecutionNotFoundApplicationException(String message) {
        super(message);
    }
}
