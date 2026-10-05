package com.carelink.application.llmmodel.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class LlmModelNotFoundApplicationException extends NotFoundApplicationException {
    public LlmModelNotFoundApplicationException(String message) {
        super(message);
    }
}
