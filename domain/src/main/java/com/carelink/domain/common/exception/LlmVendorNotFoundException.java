package com.carelink.domain.common.exception;

import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public class LlmVendorNotFoundException extends RuntimeException {
    public LlmVendorNotFoundException(LlmVendorId id) {
        super("LlmVendor not found with id: " + id.value());
    }
}
