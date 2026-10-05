package com.carelink.domain.common.exception;

import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;

public class LlmModelNotFoundException extends RuntimeException {
    public LlmModelNotFoundException(LlmModelId id) {
        super("LlmModel not found with id: " + id.value());
    }
}
