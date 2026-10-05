package com.carelink.application.llmvendor.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class LlmVendorNotFoundApplicationException extends NotFoundApplicationException {
    public LlmVendorNotFoundApplicationException(String message) {
        super(message);
    }
}
