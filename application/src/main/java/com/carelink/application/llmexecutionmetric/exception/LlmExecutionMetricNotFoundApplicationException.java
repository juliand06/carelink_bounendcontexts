package com.carelink.application.llmexecutionmetric.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class LlmExecutionMetricNotFoundApplicationException extends NotFoundApplicationException {
    public LlmExecutionMetricNotFoundApplicationException(String message) {
        super(message);
    }
}
