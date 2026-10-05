package com.carelink.application.llmexecutionerror.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class LlmExecutionErrorNotFoundApplicationException extends NotFoundApplicationException {
    public LlmExecutionErrorNotFoundApplicationException(String message) {
        super(message);
    }
}
